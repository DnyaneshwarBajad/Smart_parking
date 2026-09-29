package parking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parking.dto.request.VehicleRequest;
import parking.dto.response.VehicleResponse;
import parking.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor

public class VehicleController {


    private final VehicleService vehicleService;
    @PostMapping
    public ResponseEntity<VehicleResponse> create(
            @Valid @RequestBody VehicleRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vehicleService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                vehicleService.getById(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<VehicleResponse>> getByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                vehicleService.getByUser(userId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRequest request) {

        return ResponseEntity.ok(
                vehicleService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        vehicleService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
