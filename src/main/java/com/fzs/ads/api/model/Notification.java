package com.fzs.ads.api.model;

import com.fzs.ads.api.enums.ENotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;
    @ManyToOne(
            targetEntity = Schedule.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Schedule schedule;
    @Enumerated(EnumType.STRING)
    private ENotificationStatus status;
    private Timestamp createdOn = Timestamp.from(Instant.now());
    private Timestamp updatedOn;
}
