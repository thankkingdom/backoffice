package com.example.backoffice.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.backoffice.entity.Customer;
import com.example.backoffice.repository.CustomerRepository;

@ComponentScan(basePackages= {"com.example.backoffice"})
@EntityScan(basePackages= {"com.example.backoffice.entity"})
@EnableJpaRepositories(basePackages= {"com.example.backoffice.repository"})
// @SpringBootApplicationは以下のアノテーションを含む
// - @EnableAutoConfiguration
// - @Configuration
// - @ComponentScan
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		//println(created + " is created!");
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<Customer> page = customerRepository.findAll(pageable);
		page = customerRepository.findAllOrderByName(pageable);
		println("1ページのデータ数：" + page.getSize());
		println("現在のページ：" + page.getNumber());
		println("全ページ数：" + page.getTotalPages());
		println("全データ数：" + page.getTotalElements());
		page.getContent().forEach(System.out::println);
	}

	public static void println(Object o) {
		System.out.println(o);
	}
}
