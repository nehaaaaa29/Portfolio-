package com.example.Portfolio.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String title;          // e.g. "Full Stack Developer"

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Email
    private String email;

    private String phone;

    private String location;

    private String profileImageUrl;

    private String resumeUrl;

    // Social Links
    private String githubUrl;
    private String linkedinUrl;
    private String leetcodeUrl;
    private String twitterUrl;
    private String websiteUrl;
}
