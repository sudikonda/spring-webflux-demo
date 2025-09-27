package com.me.webflux.handler;

import com.me.webflux.model.Customer;
import com.me.webflux.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomerHandler.class);
    private final CustomerRepository customerRepository;

    public CustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest) {
        log.info("received Server Request: {}", serverRequest);
        Flux<Customer> customers = customerRepository.getCustomersWithoutDelay();
        return ServerResponse.ok().body(customers, Customer.class);
    }
}
