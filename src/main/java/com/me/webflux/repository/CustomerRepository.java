package com.me.webflux.repository;

import com.me.webflux.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.function.IntConsumer;
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

    private static void sleepExecutionSimulator(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
