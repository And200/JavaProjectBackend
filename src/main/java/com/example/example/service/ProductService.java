package com.example.example.service;


import com.example.example.domain.Product;
import com.example.example.domain.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product>listAllProduct();
    public Product getProduct(Integer id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Long id);

    public List<ProductCategory>getProductCategory(ProductCategory productCategory);
    public Product  UpdatePrice(Integer id,Integer price);


}
