package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CompanyFeedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "internshipAssignmentId", referencedColumnName = "id")
    private InternshipAssignment internshipAssignment;
}
