package com.yunnuy.che;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.yunnuy.che.model.Brand;
import com.yunnuy.che.repository.BrandRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(BrandRepository repository) {
		return (args) -> {
			// save a couple of Brands
			repository.save(new Brand("Jack", "Bauer", "www.baidu.com"));
			repository.save(new Brand("Chloe", "O'Brian", "www.baidu.com"));
			repository.save(new Brand("Kim", "Bauer", "www.baidu.com"));
			repository.save(new Brand("David", "Palmer", "www.baidu.com"));
			repository.save(new Brand("Michelle", "Dessler", "www.baidu.com"));

			// fetch all Brands
			log.info("Brands found with findAll():");
			log.info("-------------------------------");
			for (Brand Brand : repository.findAll()) {
				log.info(Brand.toString());
			}
            log.info("");

			// fetch an individual Brand by ID
			Brand Brand = repository.findOne(1L);
			log.info("Brand found with findOne(1L):");
			log.info("--------------------------------");
			log.info(Brand.toString());
            log.info("");

			// fetch Brands by last name
			log.info("Brand found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Brand bauer : repository.findByName("Bauer")) {
				log.info(bauer.toString());
			}
            log.info("");
		};
	}

}