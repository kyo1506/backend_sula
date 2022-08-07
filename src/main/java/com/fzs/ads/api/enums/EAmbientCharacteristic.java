package com.fzs.ads.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EAmbientCharacteristic {
    WIFI("Wi-Fi"),
    PROJECTOR("Projector"),
    DATACENTER("Datacenter");
    private final String Description;
}
