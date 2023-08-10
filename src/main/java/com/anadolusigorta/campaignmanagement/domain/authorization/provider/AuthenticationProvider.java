/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.authorization */

package com.anadolusigorta.campaignmanagement.domain.authorization.provider;

import com.anadolusigorta.campaignmanagement.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Log4j
@Component
@RequiredArgsConstructor
public class AuthenticationProvider {

	public Authentication getAuthentication(User user) {
		var auth = new UsernamePasswordAuthenticationToken(
				user, null,
				getAuthorities(user));
		auth.setDetails(user);
		return auth;
	}

	public List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority( user.getUserRole().getName()));

		return authorities;
	}

	public List<GrantedAuthority> getAuthorities(String roleName) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(roleName));

		return authorities;
	}
}
