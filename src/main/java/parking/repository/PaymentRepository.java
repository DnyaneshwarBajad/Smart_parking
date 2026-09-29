package parking.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import parking.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByParkingSessionId(Long parkingSessionId);

    boolean existsByParkingSessionId(Long parkingSessionId);
}
