package com.redflags.catalogo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "redflags")
public class RedFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private UUID code;

    @NotBlank(message = "Il titolo è obbligatorio")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "La descrizione è obbligatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "La persona coinvolta è obbligatoria")
    @Column(nullable = false, length = 50)
    private String personInvolved; // crush, amico/a, ex, collega, sconosciuto, altro

    @NotBlank(message = "Il contesto è obbligatorio")
    @Column(nullable = false, length = 50)
    private String context; // relazione, amicizia, lavoro, social, chat, vita reale

    @NotBlank(message = "La severità è obbligatoria")
    @Column(nullable = false, length = 10)
    private String severity; // low, medium, high

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public RedFlag() {}

    public RedFlag(String title, String description, String personInvolved, String context, String severity) {
        this.title = title;
        this.description = description;
        this.personInvolved = personInvolved;
        this.context = context;
        this.severity = severity;
    }

    // Getters and Setters
    public UUID getCode() { return code; }
    public void setCode(UUID code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPersonInvolved() { return personInvolved; }
    public void setPersonInvolved(String personInvolved) { this.personInvolved = personInvolved; }

    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "RedFlag{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", personInvolved='" + personInvolved + '\'' +
                ", context='" + context + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}