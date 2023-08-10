/* odeon_sukruo created on 21.05.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.handler.operatorhandler */

package com.anadolusigorta.campaignmanagement.domain.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OperatorHandler {

	private static final String DATE_TIME_PATTERN = "uuuu-MM-dd'T'HH:mm:ss.SSSX";

	public Boolean handleLTOperator(String productRequest, List<String> parameterValues) {
		return Double.parseDouble(productRequest.trim()) < Double.parseDouble(parameterValues.get(0).trim());
	}

	public Boolean handleGTOperator(String productRequest, List<String> parameterValues) {
		return Double.parseDouble(productRequest.trim()) > Double.parseDouble(parameterValues.get(0).trim());
	}

	public Boolean handleLTEOperator(String productRequest, List<String> parameterValues) {
		return Double.parseDouble(productRequest.trim()) <= Double.parseDouble(parameterValues.get(0).trim());
	}

	public Boolean handleGTEOperator(String productRequest, List<String> parameterValues) {
		return Double.parseDouble(productRequest.trim()) >= Double.parseDouble(parameterValues.get(0).trim());
	}

	public Boolean handleEQOperator(String productRequest, List<String> parameterValues) {
		return productRequest.trim().equals(parameterValues.get(0).trim());
	}

	public Boolean handleEQOperator(String productRequest, String parameterValue) {
		return productRequest.trim().equals(parameterValue.trim());
	}

	public Boolean handleNEQOperator(String productRequest, List<String> parameterValues) {
		return !productRequest.trim().equals(parameterValues.get(0).trim());
	}

	public Boolean handleINOperator(String productRequest, List<String> parameterValues) {
		List<String> cleanParameterValues = parameterValues.stream().map(String::trim).toList();
		return cleanParameterValues.contains(productRequest.trim());
	}

	public Boolean handleNINOperator(String productRequest, List<String> parameterValues) {
		return !handleINOperator(productRequest,parameterValues);
	}

	public Boolean handleBirthDateOperator(String productRequest) {
		return LocalDateTime.now().getMonthValue() == LocalDateTime.parse(productRequest.trim()).getMonthValue();
	}

	public Boolean handleBetweenOperator(String productRequest, List<String> parameterValues) {
		var first = parameterValues.get(0);
		var second = parameterValues.get(1);

		return (handleGTEOperator(productRequest, Collections.singletonList(first))
				&& handleLTEOperator(productRequest, Collections.singletonList(second)));
	}

	public Boolean handleAGEOperator(String productRequest, List<String> parameterValues) {
		List<Integer> cleanParameterValues = parameterValues.stream().map(String::trim).map(Integer::parseInt).sorted()
				.toList();
		var customerAge = LocalDateTime.now().getYear() - LocalDateTime.parse(productRequest.trim()).getYear();
		var isMonth = LocalDateTime.parse(productRequest.trim()).getMonthValue() - LocalDateTime.now().getMonthValue();
		if (isMonth > 0) {
			customerAge -= 1;
		}
		return cleanParameterValues.contains(customerAge);
	}

	public Boolean handleBetweenDateOperator(String productRequest, List<String> parameterValues) {
		var startDate = LocalDateTime.parse(parameterValues.get(0), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
		var endDate = LocalDateTime.parse(parameterValues.get(1), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
		var productDate = LocalDateTime.parse(productRequest.trim());

		return (startDate.isBefore(productDate) || startDate.isEqual(productDate)) &&
				(endDate.isEqual(productDate) || endDate.isAfter(productDate));
	}

	public Boolean handleLTDateOperator(String productRequest, List<String> parameterValues) {
		var date = LocalDateTime.parse(parameterValues.get(0), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
		var productDate = LocalDateTime.parse(productRequest.trim());
		return date.isAfter(productDate) || date.isEqual(productDate);
	}

	public Boolean handleGTDateOperator(String productRequest, List<String> parameterValues) {
		var date = LocalDateTime.parse(parameterValues.get(0), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
		var productDate = LocalDateTime.parse(productRequest.trim());
		return date.isBefore(productDate) || date.isEqual(productDate);
	}

}
