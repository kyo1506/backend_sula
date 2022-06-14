package com.fzs.sula.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Materia {
    HOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");
    private final String description;
}
