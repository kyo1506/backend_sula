package com.fzs.sula.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Semestre {
    PRIMEIRO("Primeiro"),
    SEGUNDO("Segundo"),
    TERCEIRO("Terceiro"),
    QUARTO("Quarto"),
    QUINTO("Quinto"),
    SEXTO("Sexto");
    private final String description;
}
