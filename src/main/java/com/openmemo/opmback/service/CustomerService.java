package com.openmemo.opmback.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openmemo.opmback.entity.customer.CustomerDto;
import com.openmemo.opmback.mapper.customer.Customer;
import com.openmemo.opmback.mapper.customer.CustomerExample;
import com.openmemo.opmback.mapper.customer.CustomerMapper;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDto> findByEmail(String email) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andEmailEqualTo(email);
        List<Customer> customerList = customerMapper.selectByExample(customerExample);
        List<CustomerDto> dtoList = setCustomerDtoList(customerList);
        return dtoList;
    }

    public int save(Customer customer) {
        return customerMapper.save(customer);
    }

    private List<CustomerDto> setCustomerDtoList(List<Customer> customerList) {
        List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
        for (Customer customer : customerList) {
            CustomerDto dto = setCustomerDto(customer);
            customerDtoList.add(dto);
        }
        return customerDtoList;
    }

    private CustomerDto setCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setUsername(customer.getUsername());
        dto.setPassword(customer.getPassword());
        dto.setRole(customer.getRole());
        return dto;
    }
}
