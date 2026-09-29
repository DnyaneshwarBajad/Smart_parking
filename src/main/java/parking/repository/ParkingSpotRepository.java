package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parking.entity.ParkingSpot;
import parking.entity.SpotStatus;
import parking.entity.SpotType;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {

    List<ParkingSpot> findByParkingLotId(Long id);
    List<ParkingSpot> findByParkingLotIdAndStatus(Long id,String status);

    Optional<ParkingSpot> findFirstByParkingLotIdAndSpotTypeAndStatus(
            Long parkingLotId,
            SpotType spotType,
            SpotStatus status
    );

    boolean existsByParkingLotIdAndSpotNumber(
            Long parkingLotId,
            String spotNumber
    );

}
