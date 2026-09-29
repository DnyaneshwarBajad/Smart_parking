package parking.mapper;

import org.springframework.stereotype.Component;
import parking.dto.request.ParkingLotRequest;
import parking.dto.request.ParkingSpotRequest;
import parking.dto.response.ParkingSpotResponse;
import parking.entity.ParkingSpot;

@Component
public class ParkingSpotMapper {
    public ParkingSpotResponse toResponse(ParkingSpot spot){
        return ParkingSpotResponse.builder()
                .id(spot.getId())
                .spotNumber(spot.getSpotNumber())
                .spotType(spot.getSpotType())
                .status(spot.getStatus())
                .parkingLotId(spot.getParkingLot().getId())
                .build();
    }

}



