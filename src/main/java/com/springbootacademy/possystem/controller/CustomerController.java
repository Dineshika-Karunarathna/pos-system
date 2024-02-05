package com.springbootacademy.possystem.controller;

import com.springbootacademy.possystem.dto.CustomerDTO;
import com.springbootacademy.possystem.dto.request.CustomerUpdateDTO;
import com.springbootacademy.possystem.service.CustomerService;
import com.springbootacademy.possystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
        String message = customerService.saveCustomer(customerDTO);
        return message;
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String message = customerService.updateCustomer(customerUpdateDTO);
        return message;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    @GetMapping(
            path = "/get-all-customers"
    )
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", allCustomers),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "delete-customer{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id") int customerId) {
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;
    }

    @GetMapping(
            path = "/get-all-customers-by-activestate{active}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "active") boolean activeState) {
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }
}

//localhost:8091/api/v1/customer
