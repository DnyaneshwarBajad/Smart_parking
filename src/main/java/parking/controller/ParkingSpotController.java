package parking.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parking.dto.request.ParkingSpotRequest;
import parking.dto.response.ParkingSpotResponse;
import parking.entity.ParkingSpot;
import parking.service.ParkingSpotService;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spots")
@RequiredArgsConstructor
public class ParkingSpotController {


    private  final ParkingSpotService parkingSpotService;


    @PostMapping
    public ResponseEntity<ParkingSpotResponse>create(@Valid @RequestBody
                                                     ParkingSpotRequest request){
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parkingSpotService.create(request));

    }

    @GetMapping("/{id}")
    public  ResponseEntity<ParkingSpotResponse>getById(@Valid @PathVariable Long id){
        return  ResponseEntity
                .ok(parkingSpotService.getById(id));
    }

    @GetMapping("/lot/{parkingLotId}")
    public ResponseEntity<List<ParkingSpotResponse>> getByParkingLot(
            @PathVariable Long parkingLotId) {

        return ResponseEntity.ok(
                parkingSpotService.getByParkingLot(parkingLotId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpotResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ParkingSpotRequest request) {

        return ResponseEntity.ok(
                parkingSpotService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void>delete(@PathVariable Long id){
        parkingSpotService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
