package net.etfbl.ip.backendApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Intership")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Intership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 2000)
    private String requirements;
}
