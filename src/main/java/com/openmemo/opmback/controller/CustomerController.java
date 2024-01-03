package com.openmemo.opmback.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openmemo.opmback.entity.common.MessageBody;
import com.openmemo.opmback.entity.customer.CustomerDto;
import com.openmemo.opmback.entity.customer.CustomerResponce;
import com.openmemo.opmback.entity.customer.get.CustomerGetResponce;
import com.openmemo.opmback.entity.customer.register.CustomerRegisterRequest;
import com.openmemo.opmback.entity.customer.register.CustomerRegisterResponce;
import com.openmemo.opmback.mapper.customer.Customer;
import com.openmemo.opmback.service.AuthorityService;
import com.openmemo.opmback.service.CustomerService;
import com.openmemo.opmback.util.ConstantUtil;

@RestController
@RequestMapping(path = "customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody CustomerRegisterRequest req) {
        try {
            List<MessageBody> messages = new ArrayList<MessageBody>();
            int registerCount = 0;
            Customer customer = new Customer();
            customer.setEmail(req.getEmail());
            customer.setPassword(passwordEncoder.encode(req.getPassword()));
            customer.setUsername(req.getUsername());
            // ここにバリデーションやチェックがあればmessagesに加える
            // emailがすでに存在している場合はエラーとする。
            List<CustomerDto> customerList = customerService.findByEmail(req.getEmail());
            if (customerList.size() != 0) {
                LOGGER.error(ConstantUtil.API_RESULT_ERROR_MESSAGE_ALREADY_EXISTS);
                MessageBody message = new MessageBody();
                message.setMessage(ConstantUtil.API_RESULT_ERROR_MESSAGE_ALREADY_EXISTS);
                messages.add(message);
            }
            if (messages.size() == 0) {
                registerCount = customerService.save(customer);
                if (registerCount == 1) {
                    authorityService.insertRoleUser(customerService.findByEmail(customer.getEmail()).get(0).getId());
                } else {
                    LOGGER.error(ConstantUtil.API_RESULT_ERROR_MESSAGE, "Error when saving user role");
                    MessageBody message = new MessageBody();
                    message.setMessage(ConstantUtil.API_RESULT_ERROR_MESSAGE);
                    messages.add(message);
                }
            }

            CustomerRegisterResponce res = new CustomerRegisterResponce();
            res.setRegisterCount(registerCount);

            if (messages.size() != 0) {
                return getResponseEntity(messages, res, HttpStatus.BAD_REQUEST);
            } else {
                return getResponseEntity(messages, res, HttpStatus.OK);
            }
        } catch (Exception e) {
            return responseError(e);
        }
    }

    @GetMapping("get")
    public ResponseEntity<Object> get(Authentication authentication) {
        try {
            List<MessageBody> messages = new ArrayList<MessageBody>();
            List<CustomerDto> customerList = new ArrayList<CustomerDto>();
            // ここにバリデーションやチェックがあればmessagesに加える
            if (messages.size() == 0) {
                customerList = customerService.findByEmail(authentication.getName());
            }

            CustomerGetResponce res = new CustomerGetResponce();
            if (customerList.size() != 1) {
                throw new BadCredentialsException("No user registered with this details!");
            } else {
                res.setEmail(customerList.get(0).getEmail());
                res.setUsername(customerList.get(0).getUsername());
                res.setId(customerList.get(0).getId());
            }

            if (messages.size() != 0) {
                return getResponseEntity(messages, res, HttpStatus.BAD_REQUEST);
            } else {
                return getResponseEntity(messages, res, HttpStatus.OK);
            }
        } catch (Exception e) {
            return responseError(e);
        }
    }
    
    @PostMapping("me")
    public ResponseEntity<Object> me(Authentication authentication) {
        try {
            List<MessageBody> messages = new ArrayList<MessageBody>();
            List<CustomerDto> customerList = new ArrayList<CustomerDto>();
            // ここにバリデーションやチェックがあればmessagesに加える
            if (messages.size() == 0) {
                customerList = customerService.findByEmail(authentication.getName());
            }

            CustomerGetResponce res = new CustomerGetResponce();
            if (customerList.size() != 1) {
                throw new BadCredentialsException("No user registered with this details!");
            } else {
                res.setEmail(customerList.get(0).getEmail());
                res.setUsername(customerList.get(0).getUsername());
                res.setId(customerList.get(0).getId());
            }

            if (messages.size() != 0) {
                return getResponseEntity(messages, res, HttpStatus.BAD_REQUEST);
            } else {
                return getResponseEntity(messages, res, HttpStatus.OK);
            }
        } catch (Exception e) {
            return responseError(e);
        }
    }

    protected ResponseEntity<Object> responseError(Exception e) {
        LOGGER.error(ConstantUtil.API_RESULT_ERROR_MESSAGE, e);

        List<MessageBody> messages = new ArrayList<MessageBody>();
        MessageBody messageBody = new MessageBody();
        messageBody.setMessage(ConstantUtil.API_RESULT_ERROR_MESSAGE);
        messages.add(messageBody);

        return getResponseEntity(messages, new CustomerResponce(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<Object> getResponseEntity(List<MessageBody> messages, CustomerResponce res,
            HttpStatus status) {
        if (messages.size() != 0) {
            res.setResult(ConstantUtil.API_RESULT_ERROR);
            res.setMessage(messages);
            return new ResponseEntity<Object>(res, new HttpHeaders(), status);
        } else {
            MessageBody messageBody = new MessageBody();
            res.setResult(ConstantUtil.API_RESULT_SUCCESS);
            messageBody.setMessage(ConstantUtil.API_RESULT_SUCCESS_MESSAGE);
            messages.add(messageBody);
            res.setMessage(messages);
            return new ResponseEntity<Object>(res, new HttpHeaders(), status);
        }
    }
}
