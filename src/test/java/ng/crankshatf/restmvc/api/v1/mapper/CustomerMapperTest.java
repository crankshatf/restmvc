package ng.crankshatf.restmvc.api.v1.mapper;

import ng.crankshatf.restmvc.api.v1.model.CustomerDTO;
import ng.crankshatf.restmvc.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Kimmel";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());
    }

}