package com.fzs.ads.api.model;

import com.fzs.ads.api.enums.ESubjectSemester;
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
public class Subject implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @Size(min = 36, max = 36)
    @NotNull
    private UUID id;

    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    @NotNull
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ESubjectSemester semester;

    @Null
    private Boolean isActive = true;

    @Null
    private Timestamp createdOn = Timestamp.from(Instant.now());

    @Null
    private Timestamp updatedOn;
}