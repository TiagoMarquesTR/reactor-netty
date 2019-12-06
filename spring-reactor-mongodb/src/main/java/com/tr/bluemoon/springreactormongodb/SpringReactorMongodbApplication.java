package com.tr.bluemoon.springreactormongodb;

import com.tr.bluemoon.springreactormongodb.model.Product;
import com.tr.bluemoon.springreactormongodb.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringReactorMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactorMongodbApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProductRepository repository) {
		return args -> {
			Flux<Product> productFlux = Flux.just(
					new Product(null, "Big Latte", 2.99),
					new Product(null, "Big Decaf", 2.99),
					new Product(null, "Green Tea", 2.99))
					.flatMap(repository::save);

			productFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
