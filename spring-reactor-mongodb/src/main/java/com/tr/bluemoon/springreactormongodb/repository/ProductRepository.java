package com.tr.bluemoon.springreactormongodb.repository;

import com.tr.bluemoon.springreactormongodb.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
