package parking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking_spots")
@Builder

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String spotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;
}
