package parking.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import parking.dto.request.ParkingSpotRequest;
import parking.dto.response.ParkingLotResponse;
import parking.dto.response.ParkingSpotResponse;
import parking.entity.ParkingLot;
import parking.entity.ParkingSpot;
import parking.entity.SpotStatus;
import parking.mapper.ParkingSpotMapper;
import parking.repository.ParkingLotRepository;
import parking.repository.ParkingSpotRepository;
import parking.service.ParkingSpotService;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private  final ParkingSpotRepository parkingSpotRepository;
    private  final ParkingLotRepository parkingLotRepository;
    private final ParkingSpotMapper parkingSpotMapper;

    @Override
    public ParkingSpotResponse create(ParkingSpotRequest request){
        ParkingLot parkingLot= parkingLotRepository.findById(request.getParkingLotId())
                .orElseThrow(()->new RuntimeException("Parking Lot Not Found"));

        if(parkingSpotRepository.existsByParkingLotIdAndSpotNumber(request.getParkingLotId(), request.getSpotNumber())){


                throw new RuntimeException("Spot number already exists in this parking lot");
            }

        ParkingSpot parkingSpot=ParkingSpot.builder()
                .spotNumber((request.getSpotNumber()))
                .id(request.getParkingLotId()).spotType(request.getSpotType()).status(SpotStatus.AVAILABLE).parkingLot(parkingLot).build();


        ParkingSpot savedSpot = parkingSpotRepository.save(parkingSpot);

        return parkingSpotMapper.toResponse(savedSpot);


    }
    @Override
    public ParkingSpotResponse getById(Long id){
    ParkingSpot parkingSpot = parkingSpotRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Parking spot not found"));

        return parkingSpotMapper.toResponse(parkingSpot);}




}
