package com.fzs.ads.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"User\"")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    @Size(min = 36, max = 36)
    @NotNull
    private UUID id;

    @Column(length = 100, nullable = false)
    @Size(min = 7, max = 100)
    @NotNull
    private String email;

    @Column(length = 100, nullable = false)
    @Size(min = 5, max = 100)
    @NotNull
    private String name;

    @Column(length = 20, nullable = false)
    @Size(min = 5, max = 20)
    @NotNull
    private String username;

    @Column(length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 7, max = 20)
    @NotNull
    private String password;

    @ManyToMany(
            targetEntity = Role.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull
    private Collection<Role> roles = new ArrayList<>();

    @Null
    private Boolean isActive = true;

    @Null
    private Timestamp createdOn = Timestamp.from(Instant.now());

    @Null
    private Timestamp updatedOn;
}
