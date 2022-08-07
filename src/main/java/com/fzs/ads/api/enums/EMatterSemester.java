package com.fzs.ads.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EMatterSemester {
    FIRST("First"),
    SECOND("Second"),
    THIRD("Third"),
    FOURTH("Fourth"),
    FIFTH("Fifth"),
    SIXTH("Sixth");
    private final String Description;
}
