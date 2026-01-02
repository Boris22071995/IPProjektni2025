package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "InternshipAssignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternshipAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "internshipId", referencedColumnName = "id")
    private Internship internship;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "internshipAssignment")
    private List<WorkLog> workLogs;

    @OneToMany(mappedBy = "internshipAssignment")
    private List<CompanyFeedback> companyFeedbacks;

    @OneToMany(mappedBy = "internshipAssignment")
    private List<FacultyGrade> facultyGrades;
}
