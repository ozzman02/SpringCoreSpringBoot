package guru.springframework.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpaDao")
public class UserServiceJpaDaoImplTest {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	public void testSaveOfUser() throws Exception {
		
		User user = new User();
		user.setUsername("someusername");
		user.setPassword("mypassword");
		
		User savedUser = userService.saveOrUpdate(user);
		
		assert savedUser.getId() != null;
		assert savedUser.getEncryptedPassword() != null;
		
		System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());
	}
	
	@Test
	public void testSaveOfUserWithCustomer() throws Exception {
		
		User user = new User();
		user.setUsername("someusername");
		user.setPassword("mypassword");
		
		Customer customer = new Customer();
		customer.setFirstName("Chevy");
		customer.setLastName("Chase");
		
		user.setCustomer(customer);
		
		User savedUser = userService.saveOrUpdate(user);
		
		assert savedUser.getId() != null;
		assert savedUser.getVersion() != null;
		assert savedUser.getCustomer() != null;
		assert savedUser.getCustomer().getId() != null;
		
	}

}
