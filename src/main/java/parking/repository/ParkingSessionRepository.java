package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import parking.entity.ParkingSession;
import parking.entity.SessionStatus;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

public interface ParkingSessionRepository
        extends JpaRepository<ParkingSession, Long>{
    Optional<ParkingSession> findByVehicleIdAndStatus(
            Long vehicleId,
            SessionStatus status
    );

    List<ParkingSession> findByVehicleId(Long vehicleId);

    boolean existsByVehicleIdAndStatus(
            Long vehicleId,
            SessionStatus status
    );


    List<ParkingSession> findByStatus(SessionStatus status);

    Page<ParkingSession> findByStatus(
            SessionStatus status,
            Pageable pageable
    );

    Page<ParkingSession> findByParkingSpotParkingLotId(
            Long parkingLotId,
            Pageable pageable
    );

    Page<ParkingSession> findByVehicleId(
            Long vehicleId,
            Pageable pageable
    );

    Page<ParkingSession> findByEntryTimeBetween(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );


    @Query("""
    SELECT ps FROM ParkingSession ps
    WHERE (:status IS NULL OR ps.status = :status)
      AND (:vehicleId IS NULL OR ps.vehicle.id = :vehicleId)
      AND (:parkingLotId IS NULL OR ps.parkingSpot.parkingLot.id = :parkingLotId)
      AND (:startTime IS NULL OR ps.entryTime >= :startTime)
      AND (:endTime IS NULL OR ps.entryTime <= :endTime)
    """)
    Page<ParkingSession> searchSessions(
            @Param("status") SessionStatus status,
            @Param("vehicleId") Long vehicleId,
            @Param("parkingLotId") Long parkingLotId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            Pageable pageable
    );

}
