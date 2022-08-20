package com.fzs.ads.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @Size(min = 36, max = 36)
    @NotNull
    private UUID id;

    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull
    private User user;

    @ManyToOne(
            targetEntity = Ambient.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull
    private Ambient ambient;

    @ManyToOne(
            targetEntity = Course.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull
    private Course course;

    @ManyToOne(
            targetEntity = Subject.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull
    private Subject subject;


    @Column(nullable = false)
    @NotNull
    private Timestamp startDate;

    @Column(nullable = false)
    @NotNull
    private Timestamp endDate;

    @Null
    private Boolean isActive = true;

    @Null
    private Timestamp createdOn = Timestamp.from(Instant.now());

    @Null
    private Timestamp updatedOn;
}