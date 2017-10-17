package guru.springframework.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Service
@Profile("jpaDao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

	@Override
	public List<Product> listAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Product.class, id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product product = em.merge(domainObject);
		em.getTransaction().commit();
		return product;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Product.class, id));
		em.getTransaction().commit();
	}

}
