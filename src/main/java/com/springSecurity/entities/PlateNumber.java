package com.springSecurity.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String plateNumber;

    private boolean isAvailable = true;

    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
