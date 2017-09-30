package guru.springframework.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpaDao")
@SuppressWarnings("unchecked")
public class ProductServiceJpaDaoImplTest {
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Test
	public void testListAllMethod() throws Exception {
		List<Product> products = (List<Product>) productService.listAll();
		assert products.size() == 5;
	}
	
	@Test
	public void testGetByIdMethod() throws Exception {
		Product product = productService.getById(1);
		assert product.getId() == 1;
	}
	
	
	
}
