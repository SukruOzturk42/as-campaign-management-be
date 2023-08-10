/* odeon_sukruo created on 28.07.2020 inside the package - tr.com.anadolusigorta.cube.application.service.security */

package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.cors;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Getter
public class CorsFilter extends OncePerRequestFilter {


	private String apiConnectAgent;

	public CorsFilter(String apiConnectAgent){
		this.apiConnectAgent=apiConnectAgent;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, PATCH");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"access-control-allow-origin,authorization, origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Expose-Headers", "Location");
		log.info(String.format("Campus Application API Request: %s", request.toString()));
		log.info(String.format("Campus Application API Response: %s", response.toString()));
		try{
			checkRequest(request);
			if ("OPTIONS".equals(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
			} else {
				filterChain.doFilter(request, response);
			}
		}catch (CampaignManagementException e){
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			response.getWriter().write(e.getKey());
		}

	}

	private void checkRequest(HttpServletRequest request){
		var isApi=request.getRequestURL().toString().contains("/api/");
		var isLoginApi=request.getRequestURL().toString().contains("/api/login") ||
				request.getRequestURL().toString().contains("/api/generate-captcha") ||
				request.getRequestURL().toString().contains("/api/single-sign-on");
		if(isApi && !isLoginApi){
           var requestAgent=request.getHeader("agent");
           log.info("agent:"+requestAgent);
           log.info("org-name:"+request.getHeader("org-name"));
           if("OPTIONS".equals(request.getMethod())){
				throw new CampaignManagementException("Method Not Allowed");
			}
			if(requestAgent==null){
				throw new CampaignManagementException("agent cannot be null");
			}
           if(!requestAgent.equals(this.apiConnectAgent)){
           	throw new CampaignManagementException("Invalid agent");
		   }
		}
	}


}
