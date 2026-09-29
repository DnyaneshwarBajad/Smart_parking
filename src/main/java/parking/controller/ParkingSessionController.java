package parking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parking.dto.request.ParkingEntryRequest;
import parking.dto.response.ParkingSessionResponse;
import parking.service.ParkingSessionService;

import java.util.List;
import parking.entity.SessionStatus;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/parking-sessions")
@RequiredArgsConstructor

public class ParkingSessionController {
    private final ParkingSessionService parkingSessionService;
    @PostMapping("/entry")
    public ResponseEntity<ParkingSessionResponse> entry(
            @Valid @RequestBody ParkingEntryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parkingSessionService.entry(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSessionResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                parkingSessionService.getById(id)
        );
    }

    @PostMapping("/{sessionId}/exit")
    public ResponseEntity<ParkingSessionResponse> exit(
            @PathVariable Long sessionId) {

        return ResponseEntity.ok(
                parkingSessionService.exit(sessionId)
        );
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ParkingSessionResponse>> getByVehicle(
            @PathVariable Long vehicleId) {

        return ResponseEntity.ok(
                parkingSessionService.getByVehicle(vehicleId)
        );
    }


    @GetMapping("/search")
    public ResponseEntity<Page<ParkingSessionResponse>> searchSessions(
            @RequestParam(required = false) SessionStatus status,
            @RequestParam(required = false) Long vehicleId,
            @RequestParam(required = false) Long parkingLotId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startTime,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "entryTime") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        return ResponseEntity.ok(
                parkingSessionService.searchSessions(
                        status,
                        vehicleId,
                        parkingLotId,
                        startTime,
                        endTime,
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }
}
