package ng.crankshatf.restmvc.services;

import ng.crankshatf.restmvc.api.v1.mapper.CustomerMapper;
import ng.crankshatf.restmvc.api.v1.model.CustomerDTO;
import ng.crankshatf.restmvc.bootstrap.Bootstrap;
import ng.crankshatf.restmvc.domain.Customer;
import ng.crankshatf.restmvc.repositories.CategoryRepository;
import ng.crankshatf.restmvc.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        System.out.println("In Customer Service Integration Test, Loading Customer Data");
        System.out.println(customerRepository.findAll().size());
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception {
        String updateName = "UpdatedName";
        Long id = getCustomerIdValue();
        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updateName);
        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer = customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(updateName, updatedCustomer.getFirstname());
        assertThat(originalFirstname, not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastname, equalTo(updatedCustomer.getLastname()));
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        Long id = getCustomerIdValue();
        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updatedName);
        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer = customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastname());
        assertThat(originalFirstname, equalTo(updatedCustomer.getFirstname()));
        assertThat(originalLastname, not(equalTo(updatedCustomer.getLastname())));
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers Found: " + customers.size());
        return customers.get(0).getId();
    }
}