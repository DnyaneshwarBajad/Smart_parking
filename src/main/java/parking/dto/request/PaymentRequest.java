package parking.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import parking.entity.PaymentMethod;

@Getter
@Setter
public class PaymentRequest {

    @NotNull(message = "Parking session ID is required")
    private Long parkingSessionId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}
