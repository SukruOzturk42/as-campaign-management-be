/* dks20165 created on 19.07.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.user.facade */

package com.anadolusigorta.campaignmanagement.domain.user.facade;

import com.anadolusigorta.campaignmanagement.domain.login.model.Login;
import com.anadolusigorta.campaignmanagement.domain.user.model.User;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserToken;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserSecurityFacade {

	private final ModelMapper modelMapper;

	private final UserRepository userRepository;






	public UserRole getUserRole() {
		return getActiveUser().getUserRole();

	}

	public String getUserFullName() {
		return getActiveUser().getFullName();

	}

	public String getUserRegionCode() {
		return getActiveUser().getUserRegionCode();

	}

	public String getUserUserName() {
		return getActiveUser().getUsername();

	}

	public String getAgencyCode() {
		return getActiveUser().getAgencyCode();

	}

	public User getActiveUser() {
		Authentication authentication = getAuthentication();
		if (Objects.isNull(authentication)) {
			return new User();
		}
		return modelMapper.map(authentication.getDetails(), User.class);
	}

	public String getEmployeeNumber() {
		return getActiveUser().getEmployeeNumber();

	}

	public void handleUserLoginEvent(User user, String token){
       var userTokens= userRepository.findUserActiveToken(user.getUsername());
	  /* if(userTokens.size()>=sessionCount){
		   var firstToken= userTokens.stream().min(Comparator.comparing(UserToken::getExpireTime));
		   firstToken.ifPresent(userRepository::deleteUserToken);
	   }*/
		userRepository.saveUserToken(UserToken.builder()
				.userName(user.getUsername())
				.token(token)
				.expireTime(LocalDateTime.now().plusMinutes(8*80))
				.build());

	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
