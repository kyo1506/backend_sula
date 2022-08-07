package com.fzs.ads.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENotificationStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    DENIED("Denied");
    private final String Description;
}
