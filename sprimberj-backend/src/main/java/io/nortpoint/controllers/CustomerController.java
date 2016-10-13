package io.nortpoint.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.nortpoint.assembler.CustomerResourceAssembler;
import io.nortpoint.beans.Customer;
import io.nortpoint.exception.EntityNotFoundException;
import io.nortpoint.store.CustomersFakeRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomersFakeRepository customersRepository;

	private final CustomerResourceAssembler customerResourceAssembler;

	@Autowired
	public CustomerController(CustomerResourceAssembler customerResourceAssembler, CustomersFakeRepository repository) {
		this.customerResourceAssembler = customerResourceAssembler;
		this.customersRepository = repository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Resource<Customer>> getCustomer(@PathVariable("id") Integer id) {
		Customer customer = customersRepository.findById(id);
		if (customer == null) {
			throw new EntityNotFoundException("Customer not found - id: " + id);
		}
		Resource<Customer> resource = customerResourceAssembler.toResource(customer);
		return ResponseEntity.ok(resource);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult)
			throws MethodArgumentNotValidException, NoSuchMethodException {
		if (customersRepository.exists(customer.getFullName())) {
			bindingResult.rejectValue("fullName", "not.unique", "Customer already exists");
		}
		if (new Random().nextInt(4) == 0) {
		    bindingResult.reject("error", "Terrible error on server side");
		}
		if (bindingResult.hasErrors()) {
			throw new MethodArgumentNotValidException(new MethodParameter(
					this.getClass().getDeclaredMethod("createCustomer", Customer.class, BindingResult.class), 0),
					bindingResult);
		}
		customersRepository.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}