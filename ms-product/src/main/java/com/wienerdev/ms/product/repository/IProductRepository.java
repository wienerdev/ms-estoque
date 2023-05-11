package com.wienerdev.ms.product.repository;

import com.wienerdev.ms.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository extends MongoRepository<Product, UUID> {
}
