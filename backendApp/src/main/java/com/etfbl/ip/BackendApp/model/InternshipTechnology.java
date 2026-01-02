package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "InternshipTechnology")
@Data
public class InternshipTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "texhnologyName", nullable = false, length = 50)
    private String technologyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internshipId", nullable = false)
    private Internship internship;
}
