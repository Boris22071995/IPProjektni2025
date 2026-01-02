package com.etfbl.ip.BackendApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "AiRecommendation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiRecommendation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "internshipId", referencedColumnName = "id")
    private Internship internship;

    private BigDecimal score;

    private String explanation;
}
