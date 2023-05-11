package com.wienerdev.ms.product.service;

import com.wienerdev.ms.product.domain.Product;
import com.wienerdev.ms.product.dto.ProductDto;
import com.wienerdev.ms.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private final IProductRepository productRepository;

    public ProductDto createProduct(ProductDto dto) {
        var entity = mapper.map(dto, Product.class);

        entity.setId(UUID.randomUUID());

        var entitySaved = productRepository.save(entity);

        log.info(
            String.valueOf(new StringBuffer().append("Employee Created: ")
                    .append("ID: ").append(entitySaved.getId())
                    .append("Nome: ").append(entitySaved.getName())
                    .append("Descricao: ").append(entitySaved.getDescription())
                    .append("Preco: ").append(entitySaved.getPrice())));

        return mapper.map(entitySaved, ProductDto.class);
    }

    public ProductDto updateProduct(ProductDto dto) {
        var entity = mapper.map(dto, Product.class);

        var entitySaved = productRepository.save(entity);

        log.info(
                String.valueOf(new StringBuffer().append("Employee Updated: ").append("ID: ")
                        .append(entitySaved.getId())
                        .append("Nome: ").append(entitySaved.getName())
                        .append("Descricao: ").append(entitySaved.getDescription())
                        .append("Preco: ").append(entitySaved.getPrice())));

        return mapper.map(entitySaved, ProductDto.class);
    }

    public List<ProductDto> getAllProducts() {
        var entities = productRepository.findAll();

        log.info("Found {} employees", entities.size());

        return entities.stream().map(product -> mapper.map(product, ProductDto.class)).toList();
    }

    public ProductDto getProductById(UUID id) {
        var entity = productRepository.findById(id);

        return mapper.map(entity, ProductDto.class);
    }

    public boolean removeProduct(UUID id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
