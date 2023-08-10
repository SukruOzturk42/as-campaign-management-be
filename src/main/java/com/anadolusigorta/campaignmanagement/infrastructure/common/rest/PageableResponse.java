/* dks20165 created on 3.01.2022 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.common.rest */

package com.anadolusigorta.campaignmanagement.infrastructure.common.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PageableResponse<T> {

	private T data;

	private ErrorResponse errors;

	private LocalDate time;

	private int page;

	private int size;

	private int totalPages;

	private long totalItems;

	private String sort;
}
