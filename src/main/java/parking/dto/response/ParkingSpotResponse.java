package parking.dto.response;

import lombok.Builder;
import lombok.Getter;
import parking.entity.SpotStatus;
import parking.entity.SpotType;
@Getter
@Builder
public class ParkingSpotResponse {

    private Long id;
    private String spotNumber;
    private SpotType spotType;
    private SpotStatus status;
    private Long parkingLotId;
}
