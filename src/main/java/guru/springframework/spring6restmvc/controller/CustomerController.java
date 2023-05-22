package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    @PatchMapping(value=CUSTOMER_PATH_ID)
    public ResponseEntity patchByCustomerId(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) {

        customerService.patchByCustomerId(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value=CUSTOMER_PATH_ID)
    public ResponseEntity deleteByCustomerId(@PathVariable("customerId") UUID customerId) {

        customerService.deleteByCustomerId(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value=CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDTO customer) {

        customerService.updateByCustomerId(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }
    @GetMapping(value=CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CUSTOMER_PATH + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
