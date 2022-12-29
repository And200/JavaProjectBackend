package com.example.example.web.rest;


import com.example.example.domain.Product;
import com.example.example.domain.ProductCategory;
import com.example.example.repository.ProductCategoryRepository;
import com.example.example.repository.ProductRepository;
import com.example.example.service.dto.CreateProductDto;
import com.example.example.service.dto.ProductDto;
import com.example.example.service.image.storage.FilesController;
import com.example.example.service.image.storage.StorageService;
import com.example.example.service.mapper.ProductToDtoConverter;
import com.example.example.web.rest.errors.ApiError;
import com.example.example.web.rest.errors.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class ProductResource {

    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private final ProductToDtoConverter productToDtoConverter;

    private final ProductCategoryRepository productCategoryRepository;

    private final StorageService storageService;

    public ProductResource(StorageService storageService,ProductCategoryRepository productCategoryRepository, ProductRepository productRepository, ProductToDtoConverter productToDtoConverter) {
        this.productCategoryRepository=productCategoryRepository;
        this.productRepository = productRepository;
        this.productToDtoConverter=productToDtoConverter;
        this.storageService=storageService;
    }






    @Operation(summary = "Obtain All products From Product Resource",description = "provider all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = " OK",content = @Content(schema = @Schema(
                    implementation = Product.class
            ))),
            @ApiResponse(responseCode = "404",description = " Not Found",content = @Content(schema = @Schema(
                  implementation =  ApiError.class
            ))),
            @ApiResponse(responseCode = "500",description = " Internal Server Error",content = @Content(schema = @Schema(
                    implementation = ApiError.class
            ))),
    })




    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>>getAllProducts(){
        List<Product>result=null;

           result=productRepository.findAll();

        List<ProductDto>query= result.stream().map(productToDtoConverter::convertToDto).collect(Collectors.toList());
        return  ResponseEntity.ok().body(query);
    }
/*
    @PostMapping("/products")
    public ResponseEntity<Product>createProduct(@Valid @RequestBody CreateProductDto productDto)throws URISyntaxException {

        if(productDto.getId() != null){
            log.debug("product id should be null");
            return  ResponseEntity.badRequest().build();
        }else if(productRepository.findByName(productDto.getName()).isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Product newProduct=new Product();
        newProduct.setName(productDto.getName());
        newProduct.setDescription(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setImage(productDto.getImage());
        ProductCategory productCategory=productCategoryRepository.findById(productDto.getCategoryId()).orElse(null);
        newProduct.setProductCategory(productCategory);
        Product result = productRepository.save(newProduct);
        this.productRepository.save(result);
        return ResponseEntity.created(new URI("/api/products/" + result.getId())).body(result);


    }


 */

    @PostMapping(value="/products",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product>createProduct(@RequestPart("nuevo") CreateProductDto productDto, @RequestPart("file")MultipartFile file)throws URISyntaxException {
    String urlImage=null;
    if(!file.isEmpty()){
        String imagen= storageService.store(file);
        urlImage= MvcUriComponentsBuilder.fromMethodName(FilesController.class,"serveFile",imagen,null).
        build().toUriString();
    }


        Product newProduct=new Product();
        newProduct.setName(productDto.getName());
        newProduct.setDescription(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setImage(urlImage);
        ProductCategory productCategory=productCategoryRepository.findById(productDto.getCategoryId()).orElse(null);
        newProduct.setProductCategory(productCategory);
        Product result = productRepository.save(newProduct);
        this.productRepository.save(result);
        return ResponseEntity.created(new URI("/api/products/" + result.getId())).body(result);

    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product>updateProduct(@Valid @RequestBody Product product){
        if(product.getId() ==null){
            return ResponseEntity.badRequest().build();
        }

        Optional <Product>query= productRepository.findById(product.getId());
        Product result=null;
        if(query.isPresent()){
            result=productRepository.save(product);
        }else{
            return  ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().body(result);
    }





    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getIdTipoDocumento(@PathVariable Integer id){
        Optional<Product> consult=null;
        try{
            consult= Optional.ofNullable(productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id)));
        }catch (ProductNotFoundException e){
            /*be careful  with the use of both types exception in the same scope of code @ControllerAdvice and ResponseStatusException
           * this is a bad practice in some cases.
            *  */
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no there's an product with these id");
        }
        ProductDto query = null;
        if(consult.isPresent()){
           query=productToDtoConverter.convertToDto(consult.get());
        }
            return ResponseEntity.ok().body(query);


    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Integer id){
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
