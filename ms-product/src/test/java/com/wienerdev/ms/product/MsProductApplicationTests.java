package com.wienerdev.ms.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wienerdev.ms.product.dto.CreateProductRequest;
import com.wienerdev.ms.product.dto.UpdateProductRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MsProductApplicationTests {

    static final MongoDBContainer mongoContainer = new MongoDBContainer("mongo:4.4.2");

	@BeforeAll
	static void start() {
		mongoContainer.start();
	}

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	void shouldCreateProduct() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/api/products")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(
							createProductRequest("Iphone 13","Iphone 13", BigDecimal.valueOf(10000)))))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldUpdateProduct() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.put("/api/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(
							updateProductRequest(
									UUID.fromString("9a126b31-6622-41d0-a197-774c7fd9f96c"),
									"Iphone 13 Pro Max",
									"Iphone 13 Pro Max",
									BigDecimal.valueOf(13000)))))
				.andExpect(status().isOk());
	}

	@Test
	void shouldGetAllProducts() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/api/products")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void shouldGetProductById() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get("/api/products/9a126b31-6622-41d0-a197-774c7fd9f96c")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void shouldDeleteProduct() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.delete("/api/products/9a126b31-6622-41d0-a197-774c7fd9f96c")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private CreateProductRequest createProductRequest(String name, String description, BigDecimal price) {
		return CreateProductRequest.builder()
				.name(name)
				.description(description)
				.price(price)
				.build();
	}

	private UpdateProductRequest updateProductRequest(UUID id, String name, String description, BigDecimal price) {
		return UpdateProductRequest.builder()
				.id(id)
				.name(name)
				.description(description)
				.price(price)
				.build();
	}

}
