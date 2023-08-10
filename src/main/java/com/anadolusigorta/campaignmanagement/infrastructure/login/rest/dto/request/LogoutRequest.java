/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request */

package com.anadolusigorta.campaignmanagement.infrastructure.login.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest {
     @NotNull
	private String token;

}
