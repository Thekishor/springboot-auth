package com.springboot_auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String verifyOtp;

    private Boolean isAccountVerified;

    private Long verifyOptExpireAt;

    private String resetOtp;

    private Long resetOtpExpiredAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
