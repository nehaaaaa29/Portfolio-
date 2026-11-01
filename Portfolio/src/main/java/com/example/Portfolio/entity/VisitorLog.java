package com.example.Portfolio.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitor_logs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VisitorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;
    private String userAgent;
    private String page;
    private String deviceType;
    private String operatingSystem;
    private String browser;
    private String referrer;
    private String country;
    private String city;
    private String screenResolution;
    private LocalDateTime visitedAt;

    @PrePersist
    public void prePersist() {
        this.visitedAt = LocalDateTime.now();
    }
}