package com.example.example.service.impl;


import com.example.example.domain.Product;
import com.example.example.domain.ProductCategory;
import com.example.example.repository.ProductRepository;
import com.example.example.service.ProductService;
import com.example.example.web.rest.errors.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    @Override
    public List<Product> listAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {

        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {

       return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
       Optional<Product> productDb=this.productRepository.findById(product.getId());
        if(productDb.isEmpty()){
            throw new ProductNotFoundException(product.getId());
        }else{
            productDb.get().setPrice(product.getPrice());
            productDb.get().setImage(product.getImage());
            productDb.get().setProductCategory(product.getProductCategory());
            productDb.get().setName(product.getName());
            productDb.get().setDescription(product.getDescription());
        }
        return this.productRepository.save(productDb.get());
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<ProductCategory> getProductCategory(ProductCategory productCategory) {
        return null;
    }

    @Override
    public Product UpdatePrice(Integer id, Integer price) {
        Product productDb=this.getProduct(id);
        if(productDb==null){
            throw new ProductNotFoundException(id);
        }
        Integer doubleStock=productDb.getPrice()+price;
        productDb.setPrice(doubleStock);
        return this.productRepository.save(productDb);
    }


}
