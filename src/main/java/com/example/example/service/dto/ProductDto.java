package com.example.example.service.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ProductDto implements Serializable {


    private Integer id;
    private String name;
    private String description;
    private String image;
    private Integer price;
    private String category;

}
