package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void testListCustomers() throws Exception {
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer());
		customerList.add(new Customer());
		
		when(customerService.listAll()).thenReturn((List) customerList);
		
		mockMvc.perform(get("/customer/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/list"))
			.andExpect(model().attribute("customers", hasSize(2)));
	}
	
	@Test
	public void testShowCustomer() throws Exception {
		
		Integer id = 1;
		
		when(customerService.getById(id)).thenReturn(new Customer());
		
		mockMvc.perform(get("/customer/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/show"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
		
	}
	
	@Test
	public void testEditCustomer() throws Exception {
		
		Integer id = 1;
		
		when(customerService.getById(id)).thenReturn(new Customer());
		
		mockMvc.perform(get("/customer/edit/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/customerform"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}
	
	@Test
	public void testNewCustomer() throws Exception {
		
		verifyZeroInteractions(customerService);
		
		mockMvc.perform(get("/customer/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("customer/customerform"))
			.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		
		Integer id = 1;
		
		String firstName = "Oscar"; 
		
		String lastName = "Santamaria";
		
		String email = "osantamaria@gmail.com";
		
		String phoneNumber = "87065587";
		
		String addressLine1 = "Santa Ana";
		
		String addressLine2 = "Condominio Avalon";
		
		String city = "San Jose";
		
		String state = "Santa Ana";
		
		String zipCode = "0909";

        Customer returnCustomer = new Customer(id, firstName, lastName, email,
        		phoneNumber, addressLine1, addressLine2, city, state, zipCode);
        
        when(customerService.saveOrUpdate(Matchers.<Customer>any())).thenReturn(returnCustomer);
        
        mockMvc.perform(post("/customer")
        	.param("id", "1")
            .param("firstName", firstName)
            .param("lastName", lastName)
            .param("email", email)
            .param("phoneNumber", phoneNumber)
            .param("addressLine1", addressLine1)
            .param("addressLine2", addressLine2)
            .param("city", city)
            .param("state", state)
            .param("zipCode", zipCode))
        		.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("addressLine1", is(addressLine1))))
                .andExpect(model().attribute("customer", hasProperty("addressLine2", is(addressLine2))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));
        
        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());
        assertEquals(addressLine1, boundCustomer.getValue().getAddressLine1());
        assertEquals(addressLine2, boundCustomer.getValue().getAddressLine2());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(state, boundCustomer.getValue().getState());
        assertEquals(zipCode, boundCustomer.getValue().getZipCode());
	
	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		
		Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
	}
	
}
