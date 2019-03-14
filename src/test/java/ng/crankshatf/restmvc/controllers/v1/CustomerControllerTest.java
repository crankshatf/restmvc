package ng.crankshatf.restmvc.controllers.v1;

import ng.crankshatf.restmvc.api.v1.model.CustomerDTO;
import ng.crankshatf.restmvc.domain.Customer;
import ng.crankshatf.restmvc.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static ng.crankshatf.restmvc.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCutomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname("Lady");
        customer1.setLastname("Gaga");
        customer1.setCustomerUrl(CustomerController.BASE_URL + "/1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Diana");
        customer2.setLastname("Ross");
        customer2.setCustomerUrl(CustomerController.BASE_URL + "/2");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get(CustomerController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomersById() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname("Lady");
        customer1.setLastname("Gaga");
        customer1.setCustomerUrl(CustomerController.BASE_URL + "/1");
        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Lady")));
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Fred");
        customer.setLastname("Milton");
        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customer.getFirstname());
        returnedDTO.setLastname(customer.getLastname());
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");
        when(customerService.createNewCustomer(customer)).thenReturn(returnedDTO);
        mockMvc.perform(post(CustomerController.BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Fred")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Jim");
        customer.setLastname("Fallon");
        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname(customer.getLastname());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");
        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class)))
                .thenReturn(returnDTO);
        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Jim")))
                .andExpect(jsonPath("$.lastname", equalTo("Fallon")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Strange");
        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstname(customer.getFirstname());
        returnDTO.setLastname("Lovefamily");
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");
        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);
        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Strange")))
                .andExpect(jsonPath("$.lastname", equalTo("Lovefamily")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService).deleteCustomerByID(anyLong());
    }
}