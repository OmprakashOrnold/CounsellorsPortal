package com.dsoft.portal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enquiries  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enqId;
    private String studentName;
    private String studentPhoto;
    private String courseName;
    private String courseMode;
    private Boolean enqStatus;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdDate;
    @UpdateTimestamp
    @Column(updatable = true)
    private LocalDate updatedDate;


}
