package com.springbootacademy.possystem.service.impl;

import com.springbootacademy.possystem.exception.NotFoundException;
import com.springbootacademy.possystem.dto.CustomerDTO;
import com.springbootacademy.possystem.dto.request.CustomerUpdateDTO;
import com.springbootacademy.possystem.entity.Customer;
import com.springbootacademy.possystem.repo.CustomerRepo;
import com.springbootacademy.possystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
            customerDTO.getCustomerId(),
            customerDTO.getCustomerName(),
            customerDTO.getCustomerAddress(),
            customerDTO.getCustomerSalary(),
            customerDTO.getContactNumbers(),
            customerDTO.getNic(),
            customerDTO.isActive()
        );

        customerRepo.save(customer);
        return "Saved "+ customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerRepo.save(customer);
            return customerUpdateDTO.getCustomerName() + " Updated Successfully";
        }
        else {
            throw new RuntimeException("No data found for this ID");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.isActive()
            );
            return customerDTO;
        }
        else {
            throw new RuntimeException("No customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();
        if (getAllCustomers.size() > 0) {

            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for (Customer customer : getAllCustomers) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getContactNumbers(),
                        customer.getNic(),
                        customer.isActive()
                );
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        }else{
            throw new NotFoundException("No Customer Found");
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);
            return "Deleted Successfully "+customerId;
        }else{
            throw new RuntimeException("No customer found in that id");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for(Customer customer : getAllCustomers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
