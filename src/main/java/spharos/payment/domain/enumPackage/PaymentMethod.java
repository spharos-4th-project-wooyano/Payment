package spharos.payment.domain.enumPackage;


import spharos.payment.global.config.CodeValue;

public enum PaymentMethod implements CodeValue {

    WAIT("0","결제대기"),
    CARD("1","카드"),
    EASY_PAYMENT("2","간편결제");


    private final String code;
    private final String value;

    PaymentMethod(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static PaymentMethod findByValue(String value) {
        for (PaymentMethod type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for code: " + value);
    }
}
