package parking.service;

import parking.dto.request.PaymentRequest;
import parking.dto.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse makePayment(PaymentRequest request);

    PaymentResponse getById(Long id);

    PaymentResponse getByParkingSession(Long parkingSessionId);
}