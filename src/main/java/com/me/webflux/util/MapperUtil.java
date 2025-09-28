package com.me.webflux.util;

import com.me.webflux.dto.ProductDto;
import com.me.webflux.model.Product;
import org.springframework.beans.BeanUtils;

public class MapperUtil {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
