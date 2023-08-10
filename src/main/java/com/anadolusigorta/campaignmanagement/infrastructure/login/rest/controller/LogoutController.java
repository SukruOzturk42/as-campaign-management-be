/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.login.rest.controller */

package com.anadolusigorta.campaignmanagement.infrastructure.login.rest.controller;

import com.anadolusigorta.campaignmanagement.domain.login.facade.LoginFacade;
import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiManagementController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.Response;
import com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request.LogoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@ApiManagementController
@RequiredArgsConstructor
public class LogoutController extends BaseController {

	private final LoginFacade loginFacade;


	@PostMapping("logout")
	public Response<?> logout(@RequestBody LogoutRequest logoutRequest) {
		return respond(loginFacade.logout(logoutRequest.getToken()));
	}



}
