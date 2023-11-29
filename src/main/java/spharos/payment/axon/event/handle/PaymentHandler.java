package spharos.payment.axon.event.handle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import spharos.payment.axon.event.PaymentSaveEvent;
import spharos.payment.domain.Payment;
import spharos.payment.domain.enumPackage.PaymentStatus;
import spharos.payment.domain.enumPackage.PaymentType;
import spharos.payment.infrastructure.PaymentRepository;

@Component
@Slf4j
@RequiredArgsConstructor
@ProcessingGroup("payE")
public class PaymentHandler {
    private final PaymentRepository paymentRepository;


    @EventHandler
    public void on(PaymentSaveEvent event) {

        PaymentType paymentType = PaymentType.fromCode(event.getPaymentType());
        PaymentStatus paymentStatus = PaymentStatus.fromCode(event.getPaymentStatus());

        Payment payment = Payment.createPayment(event.getClientEmail(), paymentType, event.getTotalAmount(),
                event.getApprovedAt(), paymentStatus);
        paymentRepository.save(payment);

    }


}

