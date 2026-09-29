package parking.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import parking.entity.SpotType;

@Getter
@Setter
public class ParkingEntryRequest {

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Parking lot ID is required")
    private Long parkingLotId;

    @NotNull(message = "Spot type is required")
    private SpotType spotType;
}
