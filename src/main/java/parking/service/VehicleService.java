package parking.service;

import org.springframework.stereotype.Service;
import parking.dto.request.VehicleRequest;
import parking.dto.response.VehicleResponse;

import java.util.List;


public interface VehicleService {

VehicleResponse create(VehicleRequest request);
VehicleResponse getById(Long id);
VehicleResponse update(Long id,VehicleRequest request);
void delete(Long id);
List<VehicleResponse> getByUser(Long userId);

}
