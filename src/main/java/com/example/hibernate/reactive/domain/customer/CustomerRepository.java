package com.example.hibernate.reactive.domain.customer;

import lombok.RequiredArgsConstructor;
import org.hibernate.reactive.stage.Stage;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

/**
 * 고객 Repository
 */
@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final Stage.SessionFactory sessionFactory;

    private static final String NAME = "name";
    private static final String SEX = "sex";
    private static final String AGE = "age";

    /**
     * 고객 저장
     *
     * @param customer 고객
     * @return CompletableFuture<Void>
     */
    public CompletableFuture<Void> save(Customer customer) {
        return sessionFactory.withTransaction(
                (session, transaction) -> session.persist(customer)
        ).toCompletableFuture();
    }

    /**
     * 고객 조회
     *
     * @param name 이름
     * @return CompletableFuture<Customer>
     */
    public CompletableFuture<Customer> get(String name) {
        return sessionFactory.withSession(
                session -> session.createQuery("from Customer where name=:name order by name", Customer.class)
                        .setParameter(NAME, name)
                        .getSingleResult()
        ).toCompletableFuture();
    }

    /**
     * 고객 업데이트
     *
     * @param customer 고객
     * @return CompletableFuture<Integer>
     */
    public CompletableFuture<Integer> update(Customer customer) {
        return sessionFactory.withTransaction(
                session -> session
                        .createQuery("update Customer set name=:name, sex=:sex, age=:age where name=:name")
                        .setParameter(NAME, customer.getName())
                        .setParameter(SEX, customer.getSex())
                        .setParameter(AGE, customer.getAge())
                        .executeUpdate()
        ).toCompletableFuture();
    }

    /**
     * 고객 삭제
     *
     * @param name 이름
     * @return CompletableFuture<Integer>
     */
    public CompletableFuture<Integer> delete(String name) {
        return sessionFactory.withTransaction(
                session -> session.createQuery("delete Customer where name=:name")
                        .setParameter(NAME, name)
                        .executeUpdate()
        ).toCompletableFuture();
    }

}
