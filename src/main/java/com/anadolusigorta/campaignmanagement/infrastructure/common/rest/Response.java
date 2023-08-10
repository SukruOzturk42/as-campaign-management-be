package com.anadolusigorta.campaignmanagement.infrastructure.common.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Response<T> {

    private T data;

    private ErrorResponse errors;

    private LocalDate time;

}
