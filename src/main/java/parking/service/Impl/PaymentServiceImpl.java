package parking.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parking.dto.request.PaymentRequest;
import parking.dto.response.PaymentResponse;
import parking.entity.ParkingSession;
import parking.entity.Payment;
import parking.entity.PaymentStatus;
import parking.entity.SessionStatus;
import parking.mapper.PaymentMapper;
import parking.repository.ParkingSessionRepository;
import parking.repository.PaymentRepository;



import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements parking.service.PaymentService {

    private final PaymentRepository paymentRepository;
    private final ParkingSessionRepository parkingSessionRepository;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public PaymentResponse makePayment(PaymentRequest request) {

        ParkingSession session = parkingSessionRepository
                .findById(request.getParkingSessionId())
                .orElseThrow(() ->
                        new RuntimeException("Parking session not found"));

        if (session.getStatus() != SessionStatus.COMPLETED) {
            throw new RuntimeException(
                    "Payment can only be made for completed parking sessions"
            );
        }

        if (paymentRepository.existsByParkingSessionId(session.getId())) {
            throw new RuntimeException(
                    "Payment already exists for this parking session"
            );
        }

        Payment payment = Payment.builder()
                .parkingSession(session)
                .amount(session.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.SUCCESS)
                .paymentTime(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .build();

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentResponse getById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse getByParkingSession(Long parkingSessionId) {

        Payment payment = paymentRepository
                .findByParkingSessionId(parkingSessionId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        return paymentMapper.toResponse(payment);
    }
}