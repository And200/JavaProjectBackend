package com.example.example.service.mapper;


import com.example.example.domain.Product;
import com.example.example.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductToDtoConverter {

    private final ModelMapper modelMapper;

    public ProductDto convertToDto(Product product){
        return modelMapper.map(product,ProductDto.class);
    }
}
