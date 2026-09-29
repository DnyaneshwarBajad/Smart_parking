package parking.mapper;

import org.springframework.stereotype.Component;
import parking.dto.response.VehicleResponse;
import parking.entity.Vehicle;

@Component
public class VehicleMapper {
    public VehicleResponse toResponse(Vehicle vehicle){
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .vehicleType(vehicle.getVehicleType())
                .userId(vehicle.getUser().getId())
                .build();
    }

}
