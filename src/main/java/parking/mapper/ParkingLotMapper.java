package parking.mapper;

import org.springframework.stereotype.Component;
import parking.dto.request.ParkingLotRequest;
import parking.dto.response.ParkingLotResponse;
import parking.entity.ParkingLot;

@Component

public class ParkingLotMapper {

    public ParkingLot toEntity(ParkingLotRequest request){
            return  ParkingLot.builder().
                name(request.getName())
                .location(request.getLocation())
                .totalSpots(request.getTotalSpots())
                .build();
    }
    public ParkingLotResponse toResponse(ParkingLot parkingLot) {
        return ParkingLotResponse.builder()
                .id(parkingLot.getId())
                .name(parkingLot.getName())
                .location(parkingLot.getLocation())
                .totalSpots(parkingLot.getTotalSpots())
                .build();
    }


}
