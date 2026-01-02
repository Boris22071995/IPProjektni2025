package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "InternshipApplication")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "internshipId", referencedColumnName = "id")
    private Internship internship;

    private String status;

    private LocalDateTime appliedAt;
}
