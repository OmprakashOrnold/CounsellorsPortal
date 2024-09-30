package com.dsoft.portal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Counsellor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long counsellorId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phNo;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdDate;
    @UpdateTimestamp
    @Column(updatable = true)
    private LocalDate updatedDate;
}
