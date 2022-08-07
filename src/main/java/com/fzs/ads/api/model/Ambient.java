package com.fzs.ads.api.model;

import com.fzs.ads.api.enums.EAmbientAvailability;
import com.fzs.ads.api.enums.EAmbientCharacteristic;
import com.fzs.ads.api.enums.EAmbientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ambient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;
    @ElementCollection(targetClass = EAmbientAvailability.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<EAmbientAvailability> availabilities;
    @ElementCollection(targetClass = EAmbientCharacteristic.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<EAmbientCharacteristic> characteristics;
    @Column(length = 50, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private EAmbientType type;
    @Column(length = 100)
    private String reference;
    private Long number;
    private Boolean isActive = true;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}