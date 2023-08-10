package com.anadolusigorta.campaignmanagement.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

    private String token;
    private LocalDateTime expireTime;

    private String userName;

    private String userRemoteAddress;
}
