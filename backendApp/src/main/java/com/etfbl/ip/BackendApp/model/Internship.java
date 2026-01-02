package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Internship")
@Data
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String requirements;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String restrictions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "internship", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InternshipTechnology> technologies;

    @OneToMany(mappedBy = "internship", fetch = FetchType.LAZY)
    private List<InternshipApplication> applications;

    @OneToMany(mappedBy = "internship", fetch = FetchType.LAZY)
    private List<InternshipAssignment> assignments;

    @OneToMany(mappedBy = "internship", fetch = FetchType.LAZY)
    private List<AiRecommendation> aiRecommendations;
}
