package ng.crankshatf.restmvc.services;

import ng.crankshatf.restmvc.api.v1.mapper.CustomerMapper;
import ng.crankshatf.restmvc.api.v1.model.CustomerDTO;
import ng.crankshatf.restmvc.domain.Customer;
import ng.crankshatf.restmvc.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michelle");
        customer1.setLastname("Obama");
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Hillary");
        customer2.setLastname("Clinton");
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        customerDTO.setLastname("Fallon");
        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        customerDTO.setLastname("Fallon");
        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        Long id = 1L;
        customerService.deleteCustomerByID(id);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}
