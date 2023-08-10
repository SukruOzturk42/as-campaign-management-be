package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.adapter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.anadolusigorta.campaignmanagement.domain.menuitem.model.MenuItem;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserToken;
import com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.notification.NotificationSendApprovalMailConfigurationProperties;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.menuitem.jpa.adapter.MenuItemJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserTokenEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.UserTokenJpaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.user.model.User;
import com.anadolusigorta.campaignmanagement.domain.user.port.UserRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity.UserRoleEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.UserRoleJpaRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {

	private final UserRoleJpaRepository userRoleJpaRepository;

	private final UserTokenJpaRepository userTokenJpaRepository;

	private final MenuItemJpaAdapter menuItemJpaAdapter;

	private final NotificationSendApprovalMailConfigurationProperties notificationSendApprovalMailConfigurationProperties;




	@Override
	public Optional<User> findByUserName(String username) {

		var user=User.builder().username("ODEON")
				.userTitle("ODEON")
				.userRole(UserRole.builder()
						.name("ADMIN")
						.auth("EDIT")
						.name("ADMIN")
						.build())
				.build();

		return Optional
				.of(user);
	}



	public User getUserFromContext() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var username = ((String) principal).split(",")[0].split("=")[1];
		return findByUserName(username.toLowerCase()).orElseThrow(() -> new CampaignManagementException("user.not.found"));
	}

	@Override
	public boolean checkUserByUserNameAndUserPassword(User user, String password) {

		return Boolean.TRUE;
	}


	@Override
	public List<User> findUsersByAgencyCode(String agencyCode) {
		return null;
	}

	@Override
	public List<UserToken> findUserActiveToken(String userName) {
		return userTokenJpaRepository.findByUserName(userName)
				.stream()
				.map(UserTokenEntity::toModel)
				.toList();
	}

	@Override
	public Optional<UserToken> findByToken(String token) {
		return userTokenJpaRepository.findByToken(token)
				.map(UserTokenEntity::toModel);
	}

	@Override
	public UserToken saveUserToken(UserToken userToken) {
		var entity= UserTokenEntity.fromModel(userToken);
		return userTokenJpaRepository.save(entity).toModel();
	}

	@Override
	@Transactional
	public UserToken deleteUserToken(UserToken userToken) {
		userTokenJpaRepository.deleteByToken(userToken.getToken());
		return userToken;
	}
}
