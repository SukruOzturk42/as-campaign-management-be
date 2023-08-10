package com.anadolusigorta.campaignmanagement.domain.user.port;

import com.anadolusigorta.campaignmanagement.domain.user.model.User;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserToken;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

	Optional<User> findByUserName(String username);

	boolean checkUserByUserNameAndUserPassword(User user, String password);

	List<User> findUsersByAgencyCode(String agencyCode);

	List<UserToken> findUserActiveToken(String userName);

	Optional<UserToken> findByToken(String token);

	UserToken saveUserToken(UserToken userToken);

	UserToken deleteUserToken(UserToken userToken);



}
