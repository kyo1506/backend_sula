package com.fzs.sula.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Caracteristica {
    WIFI("Wi-Fi"),
    PROJETOR("Projetor"),
    DATACENTER("Datacenter");
    private final String description;
}
