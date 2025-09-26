package com.me.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        // monoString here acts as a publisher
        Mono<String> monoString = Mono.just("learning-webflux").log();

        // subscriber subscribing to the publisher
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testMono_error() {
        Mono<?> monoString = Mono.just("learning-webflux")
                .then(Mono.error(new RuntimeException("Exception Occurred")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservices")
                .concatWithValues("AWS")
                .log();
        fluxString.subscribe(System.out::println);
    }

    @Test
    public void testFlux_error() {
        Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservices")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred in Flux")))
                .concatWithValues("Cloud")
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
}
