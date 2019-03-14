package ng.crankshatf.restmvc.api.v1.mapper;

import ng.crankshatf.restmvc.api.v1.model.CustomerDTO;
import ng.crankshatf.restmvc.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}
