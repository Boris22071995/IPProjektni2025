package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FacultyGrade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer grade;

    @Lob
    private String comment;

    @ManyToOne
    @JoinColumn(name = "internshipAssignmentId", referencedColumnName = "id")
    private InternshipAssignment internshipAssignment;
}
