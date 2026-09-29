package parking.service.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parking.dto.request.ParkingEntryRequest;
import parking.dto.response.ParkingSessionResponse;
import parking.entity.*;
import parking.mapper.ParkingSessionMapper;
import parking.repository.ParkingLotRepository;
import parking.repository.ParkingSessionRepository;
import parking.repository.ParkingSpotRepository;
import parking.repository.VehicleRepository;
import parking.service.ParkingSessionService;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class ParkingSessionServiceImpl implements ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSessionMapper parkingSessionMapper;


    @Override
    @Transactional
    public ParkingSessionResponse entry(ParkingEntryRequest request){
        Vehicle vehicle=vehicleRepository.findById((request.getVehicleId()))
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if(parkingSessionRepository.existsByVehicleIdAndStatus(vehicle.getId(), SessionStatus.ACTIVE)){
            throw new RuntimeException("Vehicle is already parked");
        }

        if (!parkingLotRepository.existsById(request.getParkingLotId())) {
            throw new RuntimeException("Parking lot not found");
        }


        ParkingSpot parkingSpot =
                parkingSpotRepository
                        .findFirstByParkingLotIdAndSpotTypeAndStatus(
                                request.getParkingLotId(),
                                request.getSpotType(),
                                SpotStatus.AVAILABLE
                        )
                        .orElseThrow(() ->
                                new RuntimeException("No available parking spot"));

        parkingSpot.setStatus(SpotStatus.OCCUPIED);

        ParkingSession session = ParkingSession.builder()
                .vehicle(vehicle)
                .parkingSpot(parkingSpot)
                .entryTime(LocalDateTime.now())
                .amount(0.0)
                .status(SessionStatus.ACTIVE)
                .build();
        ParkingSession savedSession =
                parkingSessionRepository.save(session);
        return parkingSessionMapper.toResponse(savedSession);
    }


    @Override
    public ParkingSessionResponse getById(Long id){
        ParkingSession session=parkingSessionRepository.findById(id).
                orElseThrow(()->
                        new RuntimeException("Parking session not found"));

        return parkingSessionMapper.toResponse(session);
    }

    @Override
    @Transactional
    public ParkingSessionResponse exit(Long sessionId) {

        ParkingSession session = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Parking session not found"));

        if (session.getStatus() != SessionStatus.ACTIVE) {
            throw new RuntimeException("Parking session is already completed");
        }

        LocalDateTime exitTime = LocalDateTime.now();

        long hours = java.time.Duration.between(
                session.getEntryTime(),
                exitTime
        ).toHours();

        // Minimum 1 hour charge
        long chargedHours = Math.max(1, hours);

        double hourlyRate = getHourlyRate(
                session.getVehicle().getVehicleType()
        );

        double amount = chargedHours * hourlyRate;

        session.setExitTime(exitTime);
        session.setAmount(amount);
        session.setStatus(SessionStatus.COMPLETED);

        ParkingSpot spot = session.getParkingSpot();
        spot.setStatus(SpotStatus.AVAILABLE);

        ParkingSession updatedSession =
                parkingSessionRepository.save(session);

        return parkingSessionMapper.toResponse(updatedSession);
    }

    private double getHourlyRate(VehicleType vehicleType) {

        return switch (vehicleType) {
            case BIKE -> 10.0;
            case CAR -> 20.0;
            case TRUCK -> 40.0;
        };
    }


    @Override
    public List<ParkingSessionResponse> getByVehicle(Long vehicleId) {

        if (!vehicleRepository.existsById(vehicleId)) {
            throw new RuntimeException("Vehicle not found");
        }

        return parkingSessionRepository.findByVehicleId(vehicleId)
                .stream()
                .map(parkingSessionMapper::toResponse)
                .toList();
    }

    @Override
    public Page<ParkingSessionResponse> searchSessions(
            SessionStatus status,
            Long vehicleId,
            Long parkingLotId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            int page,
            int size,
            String sortBy,
            String direction) {

        if (startTime != null && endTime != null
                && startTime.isAfter(endTime)) {
            throw new BadRequestException(
                    "Start time must be before or equal to end time"
            );
        }

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return parkingSessionRepository.searchSessions(
                        status,
                        vehicleId,
                        parkingLotId,
                        startTime,
                        endTime,
                        pageable
                )
                .map(parkingSessionMapper::toResponse);
    }





}
