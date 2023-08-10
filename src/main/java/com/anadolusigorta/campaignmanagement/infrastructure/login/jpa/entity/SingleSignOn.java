package com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.entity;

import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "S_SSO_LOGIN_REQUEST")
public class SingleSignOn  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String hashValue;

    @Column(length = 22)
    private String userName;

    @Column(name = "IS_VALID")
    private boolean valid;

    private LocalDateTime expiredDate;

    private LocalDateTime lastAccessDate;

    private LocalDateTime insertDate;

    @Min(0)
    @Max(12)
    @Column(columnDefinition = "int default 0")
    private Integer loginTryCount;

}
