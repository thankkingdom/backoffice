package com.example.backoffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.backoffice.entity.Customer;
import com.example.backoffice.entity.User;
import com.example.backoffice.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> findAll() {
		// return customerRepository.findAllExt();
		//return customerRepository.findByOrderByIdAsc();
		return customerRepository.findAllWithUserOrderByName();
	}

	public Customer create(Customer customer, User user) {
		customer.setUser(user);
		return customerRepository.save(customer);
	}

	public Optional<Customer> findOne(Integer id) {
		Customer c = new Customer();
		c.setId(id);

		Example<Customer> example = Example.of(c);
		return customerRepository.findOne(example);
	}

	public Customer update(Customer customer, User user) {
		customer.setUser(user);
		return customerRepository.save(customer);
	}

	public void delete(Integer id) {
		Customer customer = new Customer();
		customer.setId(id);
		customerRepository.delete(customer);
	}
}
