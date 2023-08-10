package com.anadolusigorta.campaignmanagement.domain.login.port;

import com.anadolusigorta.campaignmanagement.infrastructure.login.jpa.entity.SingleSignOn;

public interface SingleSignOnRepository {
    SingleSignOn checkSingleSignOn(String hashValue);
    void setPassive(SingleSignOn singleSignOn);
}
