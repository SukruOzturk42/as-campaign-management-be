package com.anadolusigorta.campaignmanagement.domain.login.model;

import com.anadolusigorta.campaignmanagement.domain.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private String username;
    private String password;
    private List<Integer> captcha;
    private int userCaptchaValue;

    private String ipInfo;
}
