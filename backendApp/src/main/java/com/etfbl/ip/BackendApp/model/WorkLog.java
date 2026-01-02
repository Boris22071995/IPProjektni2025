package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WorkLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer weekNumber;

    private String activities;

    @ManyToOne
    @JoinColumn(name = "internshipAssignmentId", referencedColumnName = "id")
    private InternshipAssignment internshipAssignment;
}
