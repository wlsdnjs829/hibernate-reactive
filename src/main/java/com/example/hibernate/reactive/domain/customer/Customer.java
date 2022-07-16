package com.example.hibernate.reactive.domain.customer;

import com.example.hibernate.reactive.enums.SexType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 테스트용 테이블
 */
@Table
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true, length = 100)
    private String name;

    @Column(length = 30)
    @Enumerated(value = EnumType.STRING)
    private SexType sex;

    @Column
    @Min(value = 18)
    @Max(value = 70)
    private Integer age;

    public Customer(String name, SexType sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

}
