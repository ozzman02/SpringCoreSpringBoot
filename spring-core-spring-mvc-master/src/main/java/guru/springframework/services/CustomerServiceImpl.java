package guru.springframework.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import guru.springframework.domain.Customer;

public class CustomerServiceImpl implements CustomerService {

	private Map<Integer, Customer> customers;
	
	public CustomerServiceImpl() {
		loadCustomers();
	}
	
	private void loadCustomers() {
		
		customers = new HashMap<>();
		
		Customer customer1 = new Customer(1, "Oscar", "Santamaria", "osantamaria@gmail.com", 
				"87065587", "Condominio Avalon", "Apartamento c120", "Santa Ana", "San Jose", "49433");
		
		Customer customer2 = new Customer(2, "Angello", "Kalakahua", "akala@gmail.com", 
				"87065587", "Ciudad Colon", "Ciudad Colon", "Ciudad Colon", "San Jose", "49432");
		
		Customer customer3 = new Customer(3, "Nathy", "Dutan", "nathy.dutan@gmail.com", 
				"99999999", "Residencial Bosques", "Residencial Bosques", "Santa Ana", "San Jose", "49432");
		
		Customer customer4 = new Customer(4, "Andrea", "Dutan", "andrea.dutan@gmail.com", 
				"99999999", "Residencial Bosques", "Residencial Bosques", "Santa Ana", "San Jose", "49431");
		
		customers.put(1, customer1);
		customers.put(2, customer2);
		customers.put(3, customer3);
		customers.put(4, customer4);
	}

	@Override
	public List<Customer> listAllCustomers() {
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customers.get(id);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		if (customer != null) {
			if (customer.getId() == null) {
				customer.setId(getNextKey());
			}
			customers.put(customer.getId(), customer);
			return customer;
		} else {
			throw new RuntimeException("Customer can't be null");
		}
	}

	@Override
	public void deleteCustomer(Integer id) {
		customers.remove(id);
	}
	
	private Integer getNextKey() {
		return Collections.max(customers.keySet()) + 1;
	}

}
