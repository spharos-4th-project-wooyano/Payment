package spharos.payment.global.config.toss;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Getter
public class TossPaymentConfig {

    @Value("${payment.toss.clientKey}")
    public String testClientApiKey;


    @Value("${payment.toss.secretKey}")
    private String testSecretApiKey;

    @Value("${payment.toss.success_url}")
    private String successUrl;
    @Value("${payment.toss.fail_url}")
    private String failUrl;

    public static final String URL = "https://api.tosspayments.com/v1/payments/";

}