package com.example.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.example.backoffice.entity.Customer;
import com.example.backoffice.repository.CustomerRepository;

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
