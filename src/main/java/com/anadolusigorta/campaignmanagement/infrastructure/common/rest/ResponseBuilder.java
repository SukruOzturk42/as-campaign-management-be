package com.anadolusigorta.campaignmanagement.infrastructure.common.rest;

import com.anadolusigorta.campaignmanagement.domain.common.model.PageContent;

import java.time.LocalDate;
import java.util.List;

class ResponseBuilder {

    public static <T> Response<DataResponse<T>> build(List<T> items) {
        return Response.<DataResponse<T>>builder()
                .data(DataResponse.<T>builder()
                        .items(items)
                        .build())
                .time(LocalDate.now())
                .build();
    }

    public static <T> PageableResponse<DataResponse<T>> build(PageContent<T> pageContent) {
        return PageableResponse.<DataResponse<T>>builder()
                .data(DataResponse.<T>builder()
                        .items(pageContent.getContent())
                        .build())
                .time(LocalDate.now())
                .page(pageContent.getPage())
                .size(pageContent.getSize())
                .totalItems(pageContent.getTotalItems())
                .totalPages(pageContent.getTotalPages())
                .build();
    }

    public static <T> Response<T> build(T item) {
        return Response.<T>builder()
                .time(LocalDate.now())
                .data(item).build();
    }

    public static Response<?> build(ErrorResponse errorResponse) {
        return Response.builder()
                .time(LocalDate.now())
                .errors(errorResponse)
                .build();
    }

}
