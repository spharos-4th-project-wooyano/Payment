package spharos.payment.application;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spharos.payment.global.config.toss.TossPaymentConfig;
import spharos.payment.application.dto.PaymentResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPaymentAccept {

    private final TossPaymentConfig tossPaymentConfig;


    //토스페이먼츠 외부 api 결제 승인 요청
    public PaymentResponse requestPaymentAccept(String paymentKey, String orderId, Integer amount) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        JSONObject params = new JSONObject();
        params.put("paymentKey", paymentKey);
        params.put("orderId", orderId);
        params.put("amount", amount);


        String url = TossPaymentConfig.URL + "confirm" ; //"https://api.tosspayments.com/v1/payments/confirm"
        HttpEntity<String> jsonObjectHttpEntity = new HttpEntity<>(params.toString(), headers);

        PaymentResponse paymentResponse = restTemplate.postForObject(url,
                jsonObjectHttpEntity,
                PaymentResponse.class);
        return paymentResponse;

    }

    //결제 취소
    public PaymentResponse cancelPayment(String paymentKey,String cancelReason) {
        log.info("PAY CANCEL");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        org.json.JSONObject params = new org.json.JSONObject();
        params.put("cancelReason",cancelReason);
        HttpEntity<String> jsonObjectHttpEntity = new HttpEntity<>(params.toString(), headers);
        String apiUrl = TossPaymentConfig.URL + paymentKey + "/cancel";

        PaymentResponse paymentSuccessDto = restTemplate.postForObject(apiUrl,
                jsonObjectHttpEntity,
                PaymentResponse.class);
        return paymentSuccessDto;
    }

    //헤더 필수값
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String encodedAuthKey = new String(
                Base64.getEncoder().encode((tossPaymentConfig.getTestSecretApiKey() + ":").getBytes(StandardCharsets.UTF_8)));
        headers.setBasicAuth(encodedAuthKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        UUID randomUUID = UUID.randomUUID();
        headers.set("Idempotency-Key", randomUUID.toString());
        return headers;
    }
}

