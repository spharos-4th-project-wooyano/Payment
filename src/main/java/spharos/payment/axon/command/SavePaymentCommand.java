package spharos.payment.axon.command;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spharos.payment.domain.PaymentStatus;
import spharos.payment.domain.PaymentType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SavePaymentCommand {

    private String id;

    private String clientEmail; //사업자 이메일
    private PaymentType paymentType; //결제수단  카드, 간편결제
    private int totalAmount; //결제 금액
    private LocalDateTime approvedAt; //결제 완료,취소가 일어난 날짜와 시간 정보
    private PaymentStatus paymentStatus; //결제 완료, 취소, 정산완료


}
