package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Travis")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Kyle")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Simeon")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);

    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customerMap.values());

    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(customer.getId())
                .version(customer.getVersion())
                .customerName(customer.getCustomerName())
                .updateDate(customer.getUpdateDate())
                .createdDate(customer.getCreatedDate())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateByCustomerId(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);

        existing.setCustomerName(customer.getCustomerName());
        existing.setUpdateDate(LocalDateTime.now());
        return Optional.of(existing);
    }

    @Override
    public Boolean deleteByCustomerId(UUID customerId) {
        customerMap.remove(customerId);

        return true;
    }

    @Override
    public void patchByCustomerId(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }

        customerMap.put(existing.getId(), existing);
    }
}
