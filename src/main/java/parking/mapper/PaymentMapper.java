package parking.mapper;

import org.springframework.stereotype.Component;
import parking.dto.response.PaymentResponse;
import parking.entity.Payment;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .parkingSessionId(payment.getParkingSession().getId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .paymentTime(payment.getPaymentTime())
                .transactionId(payment.getTransactionId())
                .build();
    }
}