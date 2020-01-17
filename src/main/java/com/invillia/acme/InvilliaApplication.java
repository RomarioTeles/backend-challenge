package com.invillia.acme;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.OrderStatus;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repositories.OrderRepository;
import com.invillia.acme.repositories.StoreRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories("com.invillia.acme.repositories")
public class InvilliaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(InvilliaApplication.class, args);
	}
	
	@Autowired
	private StoreRepository repository;
	
	@Autowired
	private OrderRepository orderRepository;

	
	@Override
	public void run(String... args) throws Exception {
		try {
		long countStore = repository.count();
		if(countStore == 0) {
			for(long i = 1; i < 6; i++) {
				repository.save(new Store(null, "St. Profesor Heribaldo Costa, 100 ", "Store nº "+i));
			}
			
			System.out.println("===== BUSCANDO DADOS NA BASE DE DADOS =====");
			
			repository.findAll().forEach(s -> System.out.println(s.toString()));
			
			System.out.println("===== BUSCANDO DADOS ENCONTRADOS =====");
			
		}
		
		long countOrder = orderRepository.count();
		if(countOrder == 0) {
			System.out.println("===== CRIANDO UMA ORDER =====");
			
			Order entity = new Order();
			entity.setAddress("St. 23");
			entity.setCode(12345L);
			entity.setConfirmationDate(new Date());
			entity.setStatus(OrderStatus.CREATED);
			entity.setItems(new HashSet<OrderItem>());
	
			for(long i = 1; i < 5; i++) {
				OrderItem orderItem = new OrderItem();
				orderItem.setCode(i);
				orderItem.setDescription("DESCRIPTION "+i);
				orderItem.setOrder(entity);
				orderItem.setQuantity(10L);
				orderItem.setUnitPrice(BigDecimal.TEN);
				entity.getItems().add(orderItem);
			}
			
			orderRepository.save(entity);
			
			System.out.println("===== ORDER CRIADA =====");
		}
		}catch (Exception e) {
			//ignored
		}
	}
}
