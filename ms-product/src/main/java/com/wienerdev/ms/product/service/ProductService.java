package com.wienerdev.ms.product.service;

import com.wienerdev.ms.product.domain.Product;
import com.wienerdev.ms.product.dto.CreateProductRequest;
import com.wienerdev.ms.product.dto.ProductResponse;
import com.wienerdev.ms.product.dto.UpdateProductRequest;
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

    public ProductResponse createProduct(CreateProductRequest request) {
        var entity = mapper.map(request, Product.class);

        entity.setId(UUID.randomUUID());

        var entitySaved = productRepository.save(entity);

        log.info(
            String.valueOf(new StringBuffer().append("\n --- Employee Created --- ")
                    .append("\nID: ").append(entitySaved.getId())
                    .append("\nNome: ").append(entitySaved.getName())
                    .append("\nDescricao: ").append(entitySaved.getDescription())
                    .append("\nPreco: ").append(entitySaved.getPrice())));

        return mapper.map(entitySaved, ProductResponse.class);
    }

    public ProductResponse updateProduct(UpdateProductRequest request) {
        var entity = mapper.map(request, Product.class);

        var entitySaved = productRepository.save(entity);

        log.info(
                String.valueOf(new StringBuffer().append("\n --- Employee Updated --- ")
                        .append("\nID: ").append(entitySaved.getId())
                        .append("\nNome: ").append(entitySaved.getName())
                        .append("\nDescricao: ").append(entitySaved.getDescription())
                        .append("\nPreco: ").append(entitySaved.getPrice())));

        return mapper.map(entitySaved, ProductResponse.class);
    }

    public List<ProductResponse> getAllProducts() {
        var entities = productRepository.findAll();

        log.info("Found {} employees", entities.size());

        return entities.stream().map(product -> mapper.map(product, ProductResponse.class)).toList();
    }

    public ProductResponse getProductById(UUID id) {
        var entity = productRepository.findById(id);

        return mapper.map(entity, ProductResponse.class);
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
