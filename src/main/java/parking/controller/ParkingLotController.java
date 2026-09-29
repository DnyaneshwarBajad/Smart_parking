package parking.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import parking.dto.response.ParkingLotResponse;
import  parking.service.ParkingLotService;
public class ParkingLotController {




    @GetMapping
    public ResponseEntity<Page<ParkingLotResponse>>getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        return ResponseEntity.ok(
                parkingLotService.getAll(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }




    @GetMapping("/search")
    public ResponseEntity<Page<ParkingLotResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                parkingLotService.search(
                        name,
                        location,
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }
}
