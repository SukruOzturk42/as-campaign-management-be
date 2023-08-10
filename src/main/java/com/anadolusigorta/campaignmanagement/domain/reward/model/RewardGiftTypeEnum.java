package com.anadolusigorta.campaignmanagement.domain.reward.model;

import java.util.stream.Stream;

public enum RewardGiftTypeEnum {

    GIFT("gift"),
    GIFT_TICKET("giftTicket");

    private final String value;

    RewardGiftTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RewardGiftTypeEnum of(String value) {
        return Stream.of(RewardGiftTypeEnum.values())
                .filter(status -> status.value.equals(value)).findFirst().orElseThrow();
    }

}
