package com.example.example;
import com.example.example.repository.ProductRepository;
import com.example.example.service.ProductCategoryService;
import com.example.example.service.image.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;



@SpringBootApplication
@RestController
public class ExampleApplication {


    private final ProductCategoryService productCategoryService ;
    private final ProductRepository productRepository;
    public ExampleApplication(ProductCategoryService productCategoryServiceConst,ProductRepository productRepository) {
       this.productCategoryService=productCategoryServiceConst;
       this.productRepository=productRepository;
    }

    @PostConstruct
    public void initApplication(){
        productCategoryService.insertData();
        productRepository.insertData(1,"ninguna","nothing","Pan Coco",311,1);
        productRepository.insertData(2,"ninguna","nothing","Pan Rollo",311,1);
        productRepository.insertData(3,"ninguna","nothing","Pan Pan Hojaldre",311,1);
        productRepository.insertData(4,"ninguna","nothing","Pan Hogaza",311,1);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ExampleApplication.class);

    }
    public static void main(String[] args)  {

        SpringApplication.run(ExampleApplication.class, args);
    }
    @RequestMapping(value = "/app")
    public String hello() {
        return "app running";
    }


    @Bean
    public CommandLineRunner init(StorageService storageService){
        return args ->{
            storageService.deleteAll();
            storageService.init();
        };
    }



}
