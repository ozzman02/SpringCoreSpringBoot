package guru.springframework.services;

import java.util.List;

import guru.springframework.domain.Product;

public interface ProductService {
	
	List<Product> listAllProducts();
	
	Product getProductById(Integer id);
	
	Product saveOrUpdateProduct(Product product);
	
	void deleteProduct(Integer id);
	
}
