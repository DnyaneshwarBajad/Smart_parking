package parking.entity;

import jakarta.persistence.*;
import  lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "parking_lots")

public class ParkingLot {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(nullable = false)
   private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer totalSpots;

    @OneToMany(mappedBy = "parkingLot")
    @Builder.Default
    private List<ParkingSpot>parkingSpots=new ArrayList<>();
}
