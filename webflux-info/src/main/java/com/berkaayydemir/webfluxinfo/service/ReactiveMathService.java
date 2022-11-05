package com.berkaayydemir.webfluxinfo.service;

import com.berkaayydemir.webfluxinfo.dto.MultiplyRequestDTO;
import com.berkaayydemir.webfluxinfo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(() -> input * input)
                //.map(v -> new Response((v)));
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input){
        return Flux.range(1,5)
                //.doOnNext(i -> SleepUtil.sleepSeconds(i))
                .delayElements(Duration.ofSeconds(1)) //Track the action as soon as the client cancels the request. does not do unnecessary work.
                .doOnNext(i -> System.out.println("reactive-math-service processing: " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDTO> dtoMono){
        return dtoMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }
}
