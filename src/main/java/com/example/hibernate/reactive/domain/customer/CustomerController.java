package com.example.hibernate.reactive.domain.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "고객 Controller")
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "고객 조회")
    @GetMapping(value = "/{name}")
    public Mono<CustomerDto> getCustomer(@PathVariable String name) throws InterruptedException {
        return customerService.getCustomer(name);
    }

    @PostMapping
    @Operation(summary = "고객 등록")
    public Mono<Void> save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @PutMapping
    @Operation(summary = "고객 업데이트")
    public Mono<Integer> update(@RequestBody CustomerDto customerDto) {
        return customerService.update(customerDto);
    }

    @Operation(summary = "고객 삭제")
    @DeleteMapping(value = "/{name}")
    public Mono<Integer> delete(@PathVariable String name) {
        return customerService.delete(name);
    }

}