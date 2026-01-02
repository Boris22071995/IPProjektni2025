package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CvSkill")
@Data
public class CvSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String skillName;

    @Column(nullable = false, length = 30)
    private String level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvId", nullable = false)
    private Cv cv;
}
