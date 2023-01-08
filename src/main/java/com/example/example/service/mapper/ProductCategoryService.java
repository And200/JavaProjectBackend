package com.example.example.service.mapper;

import com.example.example.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCategoryService {


  private final  ProductCategoryRepository productCategoryRepository;


    public ProductCategoryService(ProductCategoryRepository productCategoryRepositoryConstruct) {
        this.productCategoryRepository=productCategoryRepositoryConstruct;
    }

    @Transactional
    public void insertData(){
        productCategoryRepository.insertData(1,"Panes");
        productCategoryRepository.insertData(2,"Pasteles");
    }
}
