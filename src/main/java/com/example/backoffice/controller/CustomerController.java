package com.example.backoffice.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.backoffice.entity.Customer;
import com.example.backoffice.form.CustomerForm;
import com.example.backoffice.service.CustomerService;
import com.example.backoffice.service.LoginUserDetails;

@Controller
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@ModelAttribute
	CustomerForm setUpForm() {
		return new CustomerForm();
	}

	@GetMapping
	String list(Model model, Pageable pageable) {
		// List<Customer> customers = customerService.findAll();
		// model.addAttribute("customers", customers);

		if (!Optional.ofNullable(pageable).isPresent()) {
			pageable = PageRequest.of(0, 5);
		}

		Page<Customer> page = customerService.search(pageable);
		model.addAttribute("page", page);
		model.addAttribute("customers", page.getContent());

		return "customers/list";
	}

	@PreAuthorize("hasAnyRole('SYSTEM', 'ADMIN')")
	@PostMapping(path = "create")
	String create(@Validated CustomerForm form, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return list(model, null);

		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.create(customer, userDetails.getUser());
		return "redirect:/customers";
	}

	@GetMapping(path = "edit", params = "form")
	String editForm(@RequestParam Integer id, CustomerForm form) {
		Optional<Customer> customerOpt = customerService.findOne(id);
		BeanUtils.copyProperties(customerOpt.get(), form);
		return "customers/edit";
	}

	@PostMapping(path = "edit")
	String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customer.setId(id);
		customerService.update(customer, userDetails.getUser());
		return "redirect:/customers";
	}

	@PostMapping(path = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/customers";
	}

	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
}
