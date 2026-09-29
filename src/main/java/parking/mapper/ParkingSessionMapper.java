package parking.mapper;

import org.springframework.stereotype.Component;
import parking.dto.response.ParkingSessionResponse;
import parking.entity.ParkingSession;

@Component
public class ParkingSessionMapper {
    public ParkingSessionResponse toResponse(ParkingSession session) {

        return ParkingSessionResponse.builder()
                .id(session.getId())
                .vehicleId(session.getVehicle().getId())
                .vehicleNumber(session.getVehicle().getVehicleNumber())
                .parkingSpotId(session.getParkingSpot().getId())
                .spotNumber(session.getParkingSpot().getSpotNumber())
                .entryTime(session.getEntryTime())
                .exitTime(session.getExitTime())
                .amount(session.getAmount())
                .status(session.getStatus())
                .build();
    }
}
