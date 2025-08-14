package com.linkshortslinks.URL_SHORTER.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "click_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClickHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "url_id", nullable = false)
    @JsonBackReference
    private Url url;

    @Column(name = "clicked_at", nullable = false)
    private Instant clickedAt;

    @PrePersist
    protected void onCreate(){
        clickedAt = Instant.now();
    }


}
