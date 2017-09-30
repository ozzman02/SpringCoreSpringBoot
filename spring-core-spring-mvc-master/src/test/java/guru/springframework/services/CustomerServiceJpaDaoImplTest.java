package guru.springframework.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpaDao")
@SuppressWarnings("unchecked")
public class CustomerServiceJpaDaoImplTest {
	
	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Test
	public void testListAllMethod() throws Exception {
		List<Customer> customers = (List<Customer>) customerService.listAll();
		assert customers.size() == 3;
	}
	
	@Test
	public void testGetByIdMethod() throws Exception {
		Customer customer = customerService.getById(1);
		assert customer.getId() == 1;
	}
	
}
