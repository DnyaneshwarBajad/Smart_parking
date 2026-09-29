package parking.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import parking.dto.request.ParkingLotRequest;
import parking.dto.response.ParkingLotResponse;
import parking.entity.ParkingLot;
import parking.mapper.ParkingLotMapper;
import parking.repository.ParkingLotRepository;
import parking.service.ParkingLotService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService {

    private  final ParkingLotRepository parkingLotRepository;
    private final ParkingLotMapper parkingLotMapper;

    @Override
    public  ParkingLotResponse create(ParkingLotRequest request){
        ParkingLot parkingLot = parkingLotMapper.toEntity(request);

        ParkingLot savedLot = parkingLotRepository.save(parkingLot);

        return parkingLotMapper.toResponse(savedLot);
    }

    @Override
    public ParkingLotResponse getById(Long id){

        ParkingLot parkingLot=parkingLotRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Parking lot not found"));

        return  parkingLotMapper.toResponse(parkingLot);

    }

    @Override
    public List<ParkingLotResponse> getAll() {

        return parkingLotRepository.findAll()
                .stream()
                .map(parkingLotMapper::toResponse)
                .toList();
    }




    @Override
    public Page<ParkingLotResponse>getAll(
            int page,
            int size,
            String sortBy,
            String direction

    ){
        Sort sort=direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();

        Pageable pageable= PageRequest.of(page,size,
                sort);

        return parkingLotRepository
                .findAll(pageable)
                .map(parkingLotMapper::toResponse);

    }



    @Override
    public Page<ParkingLotResponse> search(
            String name,
            String location,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        Page<ParkingLot> parkingLots;

        if (name != null && !name.isBlank()
                && location != null && !location.isBlank()) {

            parkingLots =
                    parkingLotRepository
                            .findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(
                                    name,
                                    location,
                                    pageable
                            );

        } else if (name != null && !name.isBlank()) {

            parkingLots =
                    parkingLotRepository
                            .findByNameContainingIgnoreCase(
                                    name,
                                     pageable
                            );

        } else if (location != null && !location.isBlank()) {

            parkingLots =
                    parkingLotRepository
                            .findByLocationContainingIgnoreCase(
                                    location,
                                    pageable
                            );

        } else {

            parkingLots =
                    parkingLotRepository.findAll(pageable);
        }

        return parkingLots.map(parkingLotMapper::toResponse);
    }




}
