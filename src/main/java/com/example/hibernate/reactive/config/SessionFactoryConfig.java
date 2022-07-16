package com.example.hibernate.reactive.config;

import org.hibernate.reactive.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Persistence;

/**
 * 세션 팩토리 설정
 */
@Configuration
public class SessionFactoryConfig {

    private static final String PERSISTENCE_UNIT_NAME = "hibernate-reactive-persistence";

    /**
     * 세션 팩토리 반환
     * <p>
     * 영속성 단계에서 Stage & Mutiny 존재
     * <p>
     * Mutiny Uni 사용한 비동기 인터페이스
     * https://smallrye.io/smallrye-mutiny/1.6.0/#reactive-converters-built-in
     * <p>
     * Stage CompletionStage 사용한 비동기 인터페이스
     */
    @Bean
    public Stage.SessionFactory sessionFactory() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .unwrap(Stage.SessionFactory.class);
    }

}
