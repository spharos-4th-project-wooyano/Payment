package spharos.payment.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import spharos.payment.application.PaymentServiceImpl;

import spharos.payment.dto.PaymentResultResponse;
import spharos.payment.producer.PaymentEventsProducer;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentListScheduler {

   private final PaymentServiceImpl paymentService;
   private final PaymentEventsProducer paymentEventsProducer;

   @Scheduled(cron = "0 0 2 * * ?")
    public void sendMonthlyPaymentEvent() throws JsonProcessingException {
        List<PaymentResultResponse> paymentsList = paymentService.getPaymentsList();
        paymentsList.stream()
               .forEach(paymentResultResponse -> {
                   try {
                       paymentEventsProducer.sendLibraryEvent(paymentResultResponse);
                   } catch (JsonProcessingException e) {
                       throw new RuntimeException(e);
                   }
               });

   }


}
