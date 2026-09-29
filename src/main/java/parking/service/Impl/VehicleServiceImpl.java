package parking.service.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import parking.dto.request.VehicleRequest;
import parking.dto.response.VehicleResponse;
import parking.entity.Vehicle;
import parking.mapper.VehicleMapper;
import parking.repository.VehicleRepository;
import parking.service.VehicleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl  implements VehicleService {

    private  final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final VehicleMapper vehicleMapper;


    @Override
    public VehicleResponse create(VehicleRequest request){
        if(vehicleRepository.existsByVehicleNumber(request.getVehicleNumber())){
            throw new Exception("Vehicsl already register");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));


        Vehicle vehicle=Vehicle.builder()
                .vehicleNumber(request.getVehicleNumber())
                .vehicleType(request.getVehicleType())
                .user(user)
                .build();

        Vehicle savedVehicle =
                vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(savedVehicle);
    }

    @Override
    public VehicleResponse getById(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found"
                        ));

        return vehicleMapper.toResponse(vehicle);
    }

    @Override
    public List<VehicleResponse> getByUser(Long userId){
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(
                    "User not found"
            );
        }
        return vehicleRepository.findByUserId(userId)
                .stream()
                .map(vehicleMapper::toResponse)
                .toList();
    }
    @Override
    public void delete(Long id) {

        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Vehicle not found"
            );
        }

        vehicleRepository.deleteById(id);
    }


    @Override
    public VehicleResponse update(
            Long id,
            VehicleRequest request) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found"
                        ));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setUser(user);

        Vehicle updatedVehicle =
                vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(updatedVehicle);
    }
}
