package com.fzs.ads.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EAmbientType {
    LABORATORY("Laboratory"),
    CLASSROOM("Class Room"),
    LIBRARY("Library");
    private final String Description;
}
