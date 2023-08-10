/* odeon_sukruo created on 24.08.2020 inside the package - tr.com.anadolusigorta.cube.application.service.security */

package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private ApplicationContext context;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		PermissionExpressionHandler customMethodSecurityExpressionHandler = new PermissionExpressionHandler();
		customMethodSecurityExpressionHandler.setApplicationContext(context);
		return customMethodSecurityExpressionHandler;
	}

}