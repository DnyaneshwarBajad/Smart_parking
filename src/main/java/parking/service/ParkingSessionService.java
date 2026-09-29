package parking.service;

import org.springframework.data.domain.Page;
import parking.dto.request.ParkingEntryRequest;
import parking.dto.response.ParkingSessionResponse;
import parking.entity.SessionStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingSessionService {

    ParkingSessionResponse entry(ParkingEntryRequest request);

    ParkingSessionResponse getById(Long id);
    ParkingSessionResponse exit(Long sessionId);
    List<ParkingSessionResponse> getByVehicle(Long vehicleId);


    Page<ParkingSessionResponse> searchSessions(
            SessionStatus status,
            Long vehicleId,
            Long parkingLotId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            int page,
            int size,
            String sortBy,
            String direction
    );
}
