package parking.dto.response;
import lombok.Builder;
import lombok.Getter;
import parking.entity.SessionStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ParkingSessionResponse {

    private Long id;

    private Long vehicleId;
    private String vehicleNumber;

    private Long parkingSpotId;
    private String spotNumber;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private Double amount;

    private SessionStatus status;

}
