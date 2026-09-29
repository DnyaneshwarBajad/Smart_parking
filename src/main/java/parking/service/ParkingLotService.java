package parking.service;

import org.springframework.data.domain.Page;
import parking.dto.request.ParkingLotRequest;
import parking.dto.response.ParkingLotResponse;

import java.util.List;

public interface ParkingLotService {
     ParkingLotResponse create(ParkingLotRequest request);

     ParkingLotResponse getById(Long id);

     List<ParkingLotResponse>getAll();
    ParkingLotResponse update(Long id, ParkingLotRequest request);

    void delete(Long id);


    Page<ParkingLotResponse>getAll(int page,int size,String sortBy,String direction);

    Page<ParkingLotResponse> search(
            String name,
            String location,
            int page,
            int size,
            String sortBy,
            String direction
    );


}
