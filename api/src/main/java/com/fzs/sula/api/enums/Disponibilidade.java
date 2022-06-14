package com.fzs.sula.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Disponibilidade {
    MATUTINO("07:00 às 13:00"),
    VESPERTINO("13:30 às 18:00"),
    NOTURNO("18:30 às 23:00");
    private final String description;
}
