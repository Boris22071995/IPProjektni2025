package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 20)
    private String indexNumber;

    @Column(nullable = false)
    private Integer yearOfStudy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cv cv;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<InternshipApplication> applications;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<InternshipAssignment> assignments;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<AiRecommendation> aiRecommendations;

}
