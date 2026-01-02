package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Cv")
@Data
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String profileImagePath;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 45)
    private String phone;

    @Column(nullable = false, length = 45)
    private String address;

    @Column(nullable = false, length = 45)
    private String city;

    @Column(nullable = false)
    private Integer postCode;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CvExperience> experiences;

    @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CvEducation> educations;

    @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CvInterest> interests;

    @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CvSkill> skills;
}
