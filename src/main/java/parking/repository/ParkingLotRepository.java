package parking.repository;

import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import parking.entity.ParkingLot;

import java.awt.print.Pageable;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    Page<ParkingLot> findAll(Pageable pageable);
    Page<ParkingLot> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<ParkingLot> findByLocationContainingIgnoreCase(
            String location,
            Pageable pageable
    );

    Page<ParkingLot> findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(
            String name,
            String location,
            Pageable pageable
    );




}
