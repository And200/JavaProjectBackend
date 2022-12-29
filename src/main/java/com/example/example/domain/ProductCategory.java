package com.example.example.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name = "product_category", uniqueConstraints ={
        @UniqueConstraint(name = "uk_category", columnNames = {"category"})
} )
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Integer id;

    @Column(name = "category", length = 20, nullable = false)
    @NotNull
    private String category;



    @JsonIgnore
    @OneToMany(mappedBy = "productCategory")
    private Set<Product> productList;

    public ProductCategory(){

    }

    public ProductCategory(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, productList);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
