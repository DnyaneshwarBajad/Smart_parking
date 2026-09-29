package parking.dto.response;
import lombok.Builder;
import lombok.Getter;
import parking.entity.PaymentMethod;
import parking.entity.PaymentStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class PaymentResponse {

    private Long id;
    private Long parkingSessionId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentTime;
    private String transactionId;
}
