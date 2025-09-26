package com.me.webflux.repository;

import com.me.webflux.model.Customer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class CustomerRepository {

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerRepository::sleepExecutionSimulator)
                .peek(i -> System.out.println("processing count: " + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .toList();
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow: " + i))
                .map(i -> new Customer(i, "customer" + i));
    }

    private static void sleepExecutionSimulator(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
