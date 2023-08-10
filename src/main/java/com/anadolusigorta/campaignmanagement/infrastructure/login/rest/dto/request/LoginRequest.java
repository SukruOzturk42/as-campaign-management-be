/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request */

package com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.login.model.Login;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

	private String username;
	private String password;
	private List<Integer> captcha;
	private int userCaptchaValue;



	public Login toModel(HttpServletRequest request){
		return Login.builder()
				.username(username)
				.password(password)
				.captcha(captcha)
				.userCaptchaValue(userCaptchaValue)
				.ipInfo(request.getRemoteAddr())
				.build();
	}
}
