package com.example.hibernate.reactive.domain.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private static final String DELETE_CUSTOMER_FORMAT = "{} 고객 정보 : {} 삭제 완료";
    private static final String POST_CUSTOMER_INFO = "고객 이름 : {} 저장 완료";
    private static final String UPDATE_CUSTOMER_INFO = "{} 고객 업데이트 완료";

    /**
     * 고객 조회
     *
     * @param name 이름
     */
    public Mono<CustomerDto> getCustomer(String name) {
        var customerCf = customerRepository.get(name);
        return Mono.fromFuture(customerCf)
                .cast(Customer.class)
                .map(customer -> new CustomerDto(customer.getName(), customer.getSex(), customer.getAge()));
    }

    /**
     * 고객 저장
     *
     * @param customerDto 고객 객체
     */
    public Mono<Void> save(CustomerDto customerDto) {
        var customer = new Customer(customerDto.name(), customerDto.sex(), customerDto.age());
        var cfInsert = customerRepository.save(customer);
        return Mono.fromFuture(cfInsert)
                .cast(Void.class)
                .doOnNext(v -> log.info(POST_CUSTOMER_INFO, customer.getName()));
    }

    /**
     * 고객 업데이트
     *
     * @param customerDto 고객 객체
     */
    public Mono<Integer> update(CustomerDto customerDto) {
        var customer = new Customer(customerDto.name(), customerDto.sex(), customerDto.age());
        var updateCf = customerRepository.update(customer);
        return Mono.fromFuture(updateCf)
                .cast(Integer.class)
                .doOnNext(info -> log.info(UPDATE_CUSTOMER_INFO, info));
    }

    /**
     * 고객 삭제
     *
     * @param name 이름
     */
    public Mono<Integer> delete(String name) {
        var deleteCf = customerRepository.delete(name);
        return Mono.fromFuture(deleteCf)
                .cast(Integer.class)
                .doOnNext(info -> log.info(DELETE_CUSTOMER_FORMAT, info, name));
    }

}