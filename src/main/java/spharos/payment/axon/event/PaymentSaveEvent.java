package spharos.payment.axon.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentSaveEvent {

    private String orderId;

    private int amount;
    private String clientEmail;
    private String paymentKey;
    private int suppliedAmount;
    private int vat;
    private String status;
    private String method;
    private String approvedAt;
    private Long serviceId;
    private Long workerId;

    private LocalDate reservationDate;
    private String request;
    private String address;
    private LocalTime serviceStart;
    private List<Long> reservationGoodsId;
    private String userEmail;
}
