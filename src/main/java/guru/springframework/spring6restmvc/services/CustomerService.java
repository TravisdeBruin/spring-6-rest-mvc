package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateByCustomerId(UUID customerId, CustomerDTO customer);

    Boolean deleteByCustomerId(UUID customerId);

    void patchByCustomerId(UUID customerId, CustomerDTO customer);
}
