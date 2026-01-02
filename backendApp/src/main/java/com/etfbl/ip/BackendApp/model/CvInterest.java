package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CvInterest")
@Data
public class CvInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String interestName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvId", nullable = false)
    private Cv cv;
}
