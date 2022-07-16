package com.example.hibernate.reactive.domain.customer;

import com.example.hibernate.reactive.enums.SexType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "고객 객체")
public record CustomerDto(
        @Schema(description = "이름", example = "이진원") @NotNull String name,
        @Schema(description = "성별") @NotNull SexType sex,
        @Schema(description = "나이", example = "28") @NotNull Integer age) {
}
