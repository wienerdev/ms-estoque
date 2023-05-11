package com.wienerdev.ms.product.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal price;

}
