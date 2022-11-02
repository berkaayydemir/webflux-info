package com.berkaayydemir.webfluxinfo.controller;

import com.berkaayydemir.webfluxinfo.dto.MultiplyRequestDTO;
import com.berkaayydemir.webfluxinfo.dto.Response;
import com.berkaayydemir.webfluxinfo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input){
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping(value = "table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return this.reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){
        return this.reactiveMathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDTO> dto,
                                   @RequestHeader Map<String, String> headers,
                                   @RequestParam Map<String,String> params){
        System.out.println(headers);
        System.out.println(params);
        return this.reactiveMathService.multiply(dto);
    }
}
