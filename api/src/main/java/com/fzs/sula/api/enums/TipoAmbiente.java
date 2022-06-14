package com.fzs.sula.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAmbiente {
    LABORATORIO("Laboratório"),
    SALADEAULA("Sala de aula"),
    BIBLIOTECA("Biblioteca");
    private final String description;
}
