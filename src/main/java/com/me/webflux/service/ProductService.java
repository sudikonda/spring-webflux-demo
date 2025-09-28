package com.me.webflux.service;

import com.me.webflux.dto.ProductDto;
import com.me.webflux.model.Product;
import com.me.webflux.repository.ProductRepository;
import com.me.webflux.util.MapperUtil;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDto> getProducts() {
        Flux<Product> products = productRepository.findAll();
        return products.map(MapperUtil::entityToDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return productRepository.findById(id).map(MapperUtil::entityToDto);
    }

    public Flux<ProductDto> getProductsInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(MapperUtil::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(MapperUtil::entityToDto);

    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(MapperUtil::dtoToEntity))
                .doOnNext(productMono -> productMono.setId(id))
                .flatMap(productRepository::save)
                .map(MapperUtil::entityToDto);
    }
}
