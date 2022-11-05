package com.berkaayydemir.webfluxinfo.config;

import com.berkaayydemir.webfluxinfo.dto.InputFailedValidationResponse;
import com.berkaayydemir.webfluxinfo.dto.MultiplyRequestDTO;
import com.berkaayydemir.webfluxinfo.dto.Response;
import com.berkaayydemir.webfluxinfo.exception.InputValidationException;
import com.berkaayydemir.webfluxinfo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {
	@Autowired
	private ReactiveMathService reactiveMathService;

	public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
		Integer input = Integer.parseInt(serverRequest.pathVariable("input"));
		Mono<Response> responseMono = this.reactiveMathService.findSquare(input);
		return ServerResponse.ok().body(responseMono, Response.class);
	}

	public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
		Integer input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlux = this.reactiveMathService.multiplicationTable(input);
		return ServerResponse.ok().body(responseFlux, Response.class);
	}

	public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
		Integer input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlux = this.reactiveMathService.multiplicationTable(input);
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(responseFlux, Response.class);
	}

	public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
		Mono<MultiplyRequestDTO> requestDTOMono = serverRequest.bodyToMono(MultiplyRequestDTO.class);
		Mono<Response> responseMono = this.reactiveMathService.multiply(requestDTOMono);
		return ServerResponse.ok()
				.body(responseMono, Response.class);
	}

	public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
		Integer input = Integer.parseInt(serverRequest.pathVariable("input"));
		if (input < 10 || input > 20){
			return Mono.error(new InputValidationException(input));
		}

		Mono<Response> responseMono = this.reactiveMathService.findSquare(input);
		return ServerResponse.ok().body(responseMono, Response.class);
	}
}
