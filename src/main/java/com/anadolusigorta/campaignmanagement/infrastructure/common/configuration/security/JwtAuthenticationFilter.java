/* odeon_sukruo created on 24.07.2020 inside the package - tr.com.anadolusigorta.cube.application.service.security */

package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.security;

import com.anadolusigorta.campaignmanagement.domain.authorization.exception.JwtTokenException;
import com.anadolusigorta.campaignmanagement.domain.authorization.provider.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
			JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler) {
		super(new AntPathRequestMatcher("/api-admin/**"));
		this.jwtTokenProvider = jwtTokenProvider;
		this.jwtAuthenticationFailureHandler = jwtAuthenticationFailureHandler;
	}

	public static boolean nonNullAndNonEmpty(Object object) {
		boolean result = Objects.nonNull(object);
		if (object instanceof String) {
			result = result && !object.equals("");
		}
		return result;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		try {
			if (this.requiresAuthentication(request, response)) {
				Authentication authResult = attemptAuthentication(request, response);
				successfulAuthentication(request, response, filterChain, authResult);
			}
			filterChain.doFilter(request, response);
		} catch (InternalAuthenticationServiceException e) {
			SecurityContextHolder.clearContext();

			log.error(e.getMessage(), e);
			jwtAuthenticationFailureHandler.onAuthenticationFailure(response, e);
		} catch (JwtTokenException e) {
			jwtAuthenticationFailureHandler.onAuthenticationFailure(response, e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			jwtAuthenticationFailureHandler.onAuthenticationFailure(response, e);
		}

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		logger.info("===== Security Context before request =====");
		logger.info("Request for: " + request.getRequestURI());
		logger.info(SecurityContextHolder.getContext().getAuthentication());
		logger.info("===========================================");

		Authentication auth = null;
		String token = jwtTokenProvider.resolveToken(request);
		if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			auth = jwtTokenProvider.getAuthentication(token);
		}
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) {
		logger.info("===== Security Context after request =====");

		if (nonNullAndNonEmpty(authResult)) {
			MDC.put("LOGIN_NAME", authResult.getName());
		}
		logger.info("Request for: " + request.getRequestURI());
		logger.info(SecurityContextHolder.getContext().getAuthentication());
		logger.info("===========================================");

		SecurityContextHolder.getContext().setAuthentication(authResult);
	}
}
