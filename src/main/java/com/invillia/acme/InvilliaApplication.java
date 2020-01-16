package com.invillia.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.invillia.acme.domain.Store;
import com.invillia.acme.repositories.StoreRepository;

@SpringBootApplication
@EnableJpaRepositories("com.invillia.acme.repositories")
public class InvilliaApplication implements CommandLineRunner{

	@Autowired
	private StoreRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(InvilliaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		for(long i = 1; i < 6; i++) {
			repository.save(new Store(i, "St. Profesor Heribaldo Costa, 100 ", "Store nº "+i));
		}
		
		repository.findAll().forEach(s -> System.out.println());
	}
}
