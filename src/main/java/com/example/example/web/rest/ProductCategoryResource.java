package com.example.example.web.rest;


import com.example.example.repository.ProductCategoryRepository;
import com.example.example.domain.ProductCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductCategoryResource {

    private ProductCategoryRepository productCategoryRepository;


    public ProductCategoryResource(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllProductsCategories(){
        List<ProductCategory>query=productCategoryRepository.findAll();
        return  ResponseEntity.ok().body(query);
    }

    @PostMapping("/categories")
    public ResponseEntity<ProductCategory>createProductCategory(@Valid @RequestBody ProductCategory productCategory)throws URISyntaxException {

        if(productCategory.getId()!= null){
            return  ResponseEntity.badRequest().build();
        }
        ProductCategory result=productCategoryRepository.save(productCategory);
        return  ResponseEntity.created(new URI("/api/products/"+result.getId())).body(result);



    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ProductCategory>updateProduct(@Valid @RequestBody ProductCategory productCategory){
        if(productCategory.getId() ==null){
            return ResponseEntity.badRequest().build();
        }

        Optional<ProductCategory> query= productCategoryRepository.findById(productCategory.getId());
        ProductCategory result=null;
        if(query.isPresent()){
            result=productCategoryRepository.save(productCategory);
        }else{
            return  ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().body(result);
    }





    @GetMapping("/categories/{id}")
    public ResponseEntity<ProductCategory>getProductCategoryId(@PathVariable Integer id){
        Optional<ProductCategory> query=productCategoryRepository.findById(id);
        if (query.isPresent()){
            return ResponseEntity.ok().body(query.orElse(null));
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Void>deleteProductCategory(@PathVariable Integer id){
        productCategoryRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
