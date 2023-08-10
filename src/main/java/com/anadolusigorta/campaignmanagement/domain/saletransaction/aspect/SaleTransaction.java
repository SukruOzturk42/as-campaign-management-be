/* dks20165 created on 5.01.2022 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.aspect */

package com.anadolusigorta.campaignmanagement.domain.saletransaction.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SaleTransaction {
}
