package com.linkshortslinks.URL_SHORTER.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "shortend_url")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "long_url", nullable = false, length = 2048)
    private String longUrl;
    @Column(name = "short_code", nullable = false,unique = true, length = 20)
    private String shortCode;
    @Column(name="createdAt" , nullable = false,updatable = false)
    private Instant created;
    @Column(name = "updatedAt", nullable = false)
    private Instant updatedAt;
    @Column(name = "access_count", nullable = false)
    private int accessCount;


    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        created = now;
        updatedAt = now;
        accessCount = 0;
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
