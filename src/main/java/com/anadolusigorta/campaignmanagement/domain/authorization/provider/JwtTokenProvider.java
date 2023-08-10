/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.authorization.provider */

package com.anadolusigorta.campaignmanagement.domain.authorization.provider;

import com.anadolusigorta.campaignmanagement.domain.authorization.exception.JwtTokenException;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserToken;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "security")
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
	@Value("${security.token.secretKey}")
	private String secretKey;

	@Value("${security.token.expireTimeInMinutes}")
	private int expireTimeInMinutes;

	private final UserRepository userRepository;

	private static final String EXPIRED_TOKEN = "Expired JWT token";

	private final AuthenticationProvider authenticationProvider;

	public String generateToken(Authentication authentication) {
		Date now = new Date();
		int jwtExpirationInMs = expireTimeInMinutes * 60 * 1000;
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
		Claims claims = Jwts.claims().setSubject(authentication.getName());
		claims.put("details", authentication.getDetails());
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();

	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		LinkedHashMap<String,Object> details= (LinkedHashMap<String,Object>)claims.get("details");
		LinkedHashMap<String,Object> userRole=(LinkedHashMap<String,Object>)details.get("userRole");
		var roleName=(String) userRole.get("name");
		var authorities=authenticationProvider.getAuthorities(roleName);
		var authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
		authentication.setDetails(claims.get("details"));
		return authentication;
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		} else {
			return null;
		}
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
			validateUserToken(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			/*userRepository.deleteUserToken(UserToken
					.builder()
					.token(authToken)
					.build());
			log.error(EXPIRED_TOKEN);*/
			throw new JwtTokenException(EXPIRED_TOKEN);
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
			throw new JwtTokenException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
			throw new JwtTokenException("JWT claims string is empty.");
		}
		return false;
	}

	private void validateUserToken(String token){
		userRepository.findByToken(token)
				.orElseThrow(()->new JwtTokenException(EXPIRED_TOKEN));
	}

}
