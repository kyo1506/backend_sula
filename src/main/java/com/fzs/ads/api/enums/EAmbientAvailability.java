package com.fzs.ads.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EAmbientAvailability {
    MORNING("from 07:00am to 1:00pm"),
    EVENING("from 1:30pm to 6:00pm"),
    NOCTURNE("from 6:30pm to 11:00pm");
    private final String Description;
}
