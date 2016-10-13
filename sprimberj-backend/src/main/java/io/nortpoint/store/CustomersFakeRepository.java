package io.nortpoint.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.nortpoint.beans.Customer;

@Repository
public class CustomersFakeRepository {

	private static List<Customer> customers = new ArrayList<>(3);

	static {
		customers.add(new Customer(1, "Edsger Dijkstra", "11-111-111"));
		customers.add(new Customer(2, "Ada Lovelace", "22-222-222"));
		customers.add(new Customer(3, "Alan Turing", "33-333-333"));
	}

	public Customer findById(int id) {
		for (Customer c : customers) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	public void save(Customer customer) {
		int maxId = customers.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId();
		customer.setId(++maxId);
		customers.add(customer);
	}

	public boolean exists(final String fullName) {
		return customers.stream().anyMatch(c -> fullName.equals(c.getFullName()));
	}

}