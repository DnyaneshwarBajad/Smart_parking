package parking.dto.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class ParkingLotRequest {
    @NotBlank(message="name is req")
    private String name;

    @NotBlank(message="location is req")
    private String location;

    @Min(value = 1, message = "Total spots must be at least 1")
    private Integer totalSpots;
}
