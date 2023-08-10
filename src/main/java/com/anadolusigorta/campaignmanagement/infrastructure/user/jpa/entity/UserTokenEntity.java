package com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
import com.anadolusigorta.campaignmanagement.domain.user.model.UserToken;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_token")
public class UserTokenEntity extends AbstractEntity {

    @Column(name = "userName", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_ip")
    private String userIp;

    @Column(name = "token",unique = true, nullable = false, length = 4000)
    private String token;

    @Column(name = "expire_time", length = 400)
    private LocalDateTime expireTime;


    public UserToken toModel() {
        return UserToken.builder()
                .userName(userName)
                .token(token)
                .expireTime(expireTime)
                .build();
    }

    public static UserTokenEntity fromModel(UserToken userToken) {
        var entity =new UserTokenEntity();
        entity.setUserName(userToken.getUserName());
        entity.setToken(userToken.getToken());
        entity.setUserIp(userToken.getUserRemoteAddress());
        entity.setExpireTime(userToken.getExpireTime());
        return entity;
    }

}
