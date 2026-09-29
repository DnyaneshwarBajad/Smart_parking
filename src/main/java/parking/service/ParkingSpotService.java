package parking.service;

import parking.dto.request.ParkingSpotRequest;
import parking.dto.response.ParkingSpotResponse;

import java.util.List;


public interface ParkingSpotService {
    ParkingSpotResponse create(ParkingSpotRequest request);

    ParkingSpotResponse getById(Long id);

    List<ParkingSpotResponse> getByParkingLot(Long parkingLotId);

    ParkingSpotResponse update(Long id, ParkingSpotRequest request);

    void delete(Long id);
}
