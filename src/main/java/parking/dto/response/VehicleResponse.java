package parking.dto.response;

import lombok.Builder;
import lombok.Getter;
import parking.entity.VehicleType;

@Getter
@Builder
public class VehicleResponse {

    private Long id;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Long userId;
}
