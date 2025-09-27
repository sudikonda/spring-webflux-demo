package com.me.webflux.handler;

import com.me.webflux.model.Customer;
import com.me.webflux.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomerStreamHandler.class);
    private final CustomerRepository customerRepository;

    public CustomerStreamHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<ServerResponse> getCustomersStream(ServerRequest serverRequest) {
        log.info("received request: {}", serverRequest);
        Flux<Customer> customersStream = customerRepository.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
}
