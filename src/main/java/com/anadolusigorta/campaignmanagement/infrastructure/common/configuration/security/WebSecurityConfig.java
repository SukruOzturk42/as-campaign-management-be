/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.security */

package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.security;

import com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.cors.CorsFilterConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.anadolusigorta.campaignmanagement.domain.authorization.provider.JwtTokenProvider;
import com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.cors.CorsFilter;
import lombok.RequiredArgsConstructor;

;

@Configuration
@EnableWebSecurity(debug = true)
@Primary
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;
	private final JwtTokenProvider jwtTokenProvider;
	private final CorsFilterConfigurationProperties corsFilterConfigurationProperties;
	private static final String[] AUTH_WHITELIST = {
			"/api/**",
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			"/v3/api-docs/**",
			"/swagger-ui/**"
	};

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/v2/api-docs", "/css/**")
				.antMatchers(AUTH_WHITELIST)
				.antMatchers("/configuration/**")
				.antMatchers("/webjars")
				.antMatchers("/public", "/stomp/**")
				.antMatchers("h2-console/**/**")
				.antMatchers("/actuator/**");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		JwtAuthenticationFilter jwtTokenFilter = new JwtAuthenticationFilter(jwtTokenProvider,
				jwtAuthenticationFailureHandler);
		httpSecurity.anonymous().and().httpBasic().disable().formLogin().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().rememberMe().disable().logout().disable()
				.formLogin().disable().x509().disable().requestCache().disable()
				.addFilterBefore(new CorsFilter(corsFilterConfigurationProperties.getAgent()), ChannelProcessingFilter.class);
		httpSecurity.authorizeRequests().
				 antMatchers(AUTH_WHITELIST)
				.permitAll()
				.antMatchers("/api-admin/cm-campaign/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","GMUser","PMYMUnauthorized")
				.antMatchers("/api-admin/cm-rule/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized")
				.antMatchers("/api-admin/cm-code/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","GMUser","PMYMUnauthorized")
				.antMatchers("/api-admin/cm-policy-sale/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","GMUser")
				.antMatchers("/api-admin/cm-record/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","Admin")
				.antMatchers("/api-admin/cm-contact/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","PMYMUnauthorized","GMUser")
				.antMatchers("/api-admin/cm-dashboard/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","GMUser","PMYMUnauthorized")
				.antMatchers("/api-admin/cm-task-management/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","AgencyUser","RegionalUser","BSM","MSU","GMUser","PMYMUnauthorized")
				.antMatchers("/api-admin/**").hasAnyAuthority("PMYMSupervisor","PMYMAuthorized","PMYMUnauthorized","GMUser","AgencyUser","RegionalUser","BSM","MSU","Admin")
				.anyRequest().authenticated();

		httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers()
				.xssProtection()
				.and()
				.contentSecurityPolicy("script-src 'self'");

	}
}
