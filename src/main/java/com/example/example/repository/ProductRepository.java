package com.example.example.repository;

import com.example.example.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Modifying
    @Transactional
    @Query(value = "  INSERT INTO product (id,description,image,name,price,id_category) VALUES(?1,?2,?3,?4,?5,?6); ",nativeQuery = true)
    void insertData(Integer id,String description,String image,String name,Integer price,Integer categoryId);

    Optional<Product> findByName(String name);

}
