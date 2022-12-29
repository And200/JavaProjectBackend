package com.example.example.domain;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;



    @Schema(defaultValue ="ID of the product",example = "1",minProperties = 1)


    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Integer id;


    @Schema(defaultValue ="Name OF the product",example = "Pan Rollo",minProperties = 2)


    @NotNull
    @Size(max=50)
    @Column(name = "name",nullable = false)
    private String name;



    @Schema(defaultValue ="price",example = "333",minProperties = 2)


    @NotNull
    @Column(name = "price",length = 50,nullable = false)
    private Integer price;


    @Schema(defaultValue ="description",example = "Pan Rollo Con queso",minProperties = 2)


    @NotNull
    @Column(name = "description",length = 50,nullable = false)
    private String description;
    @NotNull
    @Column(name = "image",length = 50,nullable = false)
    private String image;



    @Schema(defaultValue ="Product Category",example = "1",minProperties = 2)

    @ManyToOne(optional = false)
    @JoinColumn(name="id_category",referencedColumnName = "id",
            foreignKey = @ForeignKey(name="fk_category"))
    @NotNull
    private ProductCategory productCategory;


    public Product(Integer id, String name, Integer price, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;

    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(description, product.description) && Objects.equals(image, product.image) && Objects.equals(productCategory, product.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, image, productCategory);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}




