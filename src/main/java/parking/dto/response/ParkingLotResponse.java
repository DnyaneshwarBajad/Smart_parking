package parking.dto.response;

import lombok.Builder;
import lombok.Getter;
@Builder

@Getter
public class ParkingLotResponse {

    private Long id;
    private String name;
    private String location;
    private Integer totalSpots;
}
