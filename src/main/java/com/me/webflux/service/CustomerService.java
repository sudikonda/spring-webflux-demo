package com.me.webflux.service;

import com.me.webflux.model.Customer;
import com.me.webflux.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {


    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerRepository.getCustomers();
        long end = System.currentTimeMillis();
        log.info("Total execution time: {}", end - start);
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customerFlux = customerRepository.getCustomersStream();
        long end = System.currentTimeMillis();
        log.info("Total execution time: {}", end - start);
        return customerFlux;
    }
}
