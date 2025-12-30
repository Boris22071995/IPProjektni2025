package net.etfbl.ip.backendApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "indexNumber", nullable = false)
    private String indexNumber;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "yearOfStudy")
    private Integer yearOfStudy;

}
