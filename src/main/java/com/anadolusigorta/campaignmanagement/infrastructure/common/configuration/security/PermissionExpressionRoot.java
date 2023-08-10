/* odeon_sukruo created on 24.08.2020 inside the package - tr.com.anadolusigorta.cube.application.service.security */

package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.security.Permission;
import java.util.Arrays;

public class PermissionExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
	private Object filterObject;
	private Object returnObject;
	private Object target;

	public PermissionExpressionRoot(Authentication authentication) {
		super(authentication);
	}

	public boolean checkPermission(Permission... permissions) {
		return authentication.getAuthorities().containsAll(Arrays.asList(permissions));
	}

	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	public Object getFilterObject() {
		return filterObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	/**
	 * Sets the "this" property for use in expressions. Typically this will be the "this"
	 * property of the {@code JoinPoint} representing the method invocation which is being
	 * protected.
	 *
	 * @param target the target object on which the method in is being invoked.
	 */
	void setThis(Object target) {
		this.target = target;
	}

	public Object getThis() {
		return target;
	}
}
