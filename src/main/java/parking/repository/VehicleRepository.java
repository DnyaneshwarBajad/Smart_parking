package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parking.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);

    boolean existsByVehicleNumber(String vehicleNumber);

    List<Vehicle> findByUserId(Long userId);


}
