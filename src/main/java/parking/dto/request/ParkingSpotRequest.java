package parking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import parking.entity.SpotType;
@Getter
@Setter

public class ParkingSpotRequest {

    @NotBlank(message = "Spot number is required")
    private String spotNumber;

    @NotNull(message = "Spot type is required")
    private SpotType spotType;

    @NotNull(message = "Parking lot ID is required")
    private Long parkingLotId;

}
