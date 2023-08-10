/* odeon_sukruo created on 24.08.2020 inside the package - tr.com.anadolusigorta.cube.application.service.security */

package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFailureHandler {

	private ObjectMapper objectMapper;


	public JwtAuthenticationFailureHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;

	}

	public void onAuthenticationFailure(HttpServletResponse response,Exception e) throws
			IOException {

		 var errorMessage=e.getMessage();
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		objectMapper.writeValue(response.getOutputStream(), errorMessage);
		response.flushBuffer();
	}
}
