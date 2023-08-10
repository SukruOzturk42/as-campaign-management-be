package com.anadolusigorta.campaignmanagement.domain.login.facade;

import com.anadolusigorta.campaignmanagement.domain.menuitem.facade.MenuItemFacade;
import com.anadolusigorta.campaignmanagement.domain.user.facade.UserSecurityFacade;
import com.anadolusigorta.campaignmanagement.domain.user.model.User;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserToken;
import com.anadolusigorta.campaignmanagement.infrastructure.captcha.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.anadolusigorta.campaignmanagement.domain.authorization.provider.AuthenticationProvider;
import com.anadolusigorta.campaignmanagement.domain.authorization.provider.JwtTokenProvider;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.login.model.Login;
import com.anadolusigorta.campaignmanagement.domain.login.port.SingleSignOnRepository;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginFacade {

	private final UserRepository userRepository;
	private final AuthenticationProvider authenticationProvider;
	private final JwtTokenProvider jwtTokenProvider;
	private final SingleSignOnRepository singleSignOnRepository;
	private final UserSecurityFacade userSecurityFacade;
	private final CaptchaService captchaService;

	public String login(Login login) {

		var user = userRepository.findByUserName(login.getUsername())
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.USER_NOT_FOUND));
		var isLoginDataCorrect = userRepository.checkUserByUserNameAndUserPassword(user,
				login.getPassword());

		if (isLoginDataCorrect) {
			 var token=tokenGeneration(user);
			userSecurityFacade.handleUserLoginEvent(user,token);
			return token;
		} else {
			throw new CampaignManagementException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

	public String logout(String token) {
       /* userRepository.deleteUserToken(UserToken.builder()
						.token(token)
				.build());*/

		return token;
	}

	private String tokenGeneration(User user) {
		var authentication = authenticationProvider.getAuthentication(user);
		return jwtTokenProvider.generateToken(authentication);
	}

	public String singleSignOn(Boolean withoutToolbars, String withoutIsam) {
		if (withoutToolbars.equals(true)) {
			var singleSignOnResponse = singleSignOnRepository.checkSingleSignOn(withoutIsam);
			if(singleSignOnResponse == null) {
				log.info("Single sign on failed with withoutToolbars: " + withoutToolbars.toString() + " and withoutIsam: " + withoutIsam);
				throw new CampaignManagementException(ExceptionConstants.SINGLE_SIGN_ON_FAILED);
			}
			singleSignOnRepository.setPassive(singleSignOnResponse);
			var user = userRepository.findByUserName(singleSignOnResponse.getUserName())
					.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.USER_NOT_FOUND));
			var token=tokenGeneration(user);
			userSecurityFacade.handleUserLoginEvent(user,token);
			return token;
		} else {
			log.info("Single sign on failed with withoutToolbars: false and user not found exception thrown");
			throw new CampaignManagementException(ExceptionConstants.USER_NOT_FOUND);
		}
	}

}
