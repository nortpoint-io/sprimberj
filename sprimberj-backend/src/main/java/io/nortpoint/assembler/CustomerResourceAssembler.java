package io.nortpoint.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import io.nortpoint.beans.Customer;

@Component
public class CustomerResourceAssembler implements ResourceAssembler<Customer, Resource<Customer>> {

  @Override
    public Resource<Customer> toResource(Customer customer) {
        Resource<Customer> resource = new Resource<>(customer);
        resource.add(new Link("http://customers/" + customer.getId()).withSelfRel());
        return resource;
    }
  
}