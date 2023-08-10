/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.login.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.login.rest.controller;

import com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request.LogoutRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anadolusigorta.campaignmanagement.domain.login.facade.LoginFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request.LoginRequest;
import com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request.SingleSignOnRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@ApiController
@RequiredArgsConstructor
public class LoginController extends BaseController {

	private final LoginFacade loginFacade;

	@PostMapping("login")
	public Response<?> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
		return respond(loginFacade.login(loginRequest.toModel(request)));
	}

	@PostMapping("logout")
	public Response<?> logout(@RequestBody LogoutRequest logoutRequest) {
		return respond(loginFacade.logout(logoutRequest.getToken()));
	}

	@PostMapping("single-sign-on")
	public Response<?> sso(@RequestBody SingleSignOnRequest singleSignOnRequest) {
		return respond(loginFacade.singleSignOn(singleSignOnRequest.getWithoutToolbars(),
				singleSignOnRequest.getWithoutIsam()));
	}

}
