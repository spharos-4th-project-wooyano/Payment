package spharos.payment.domain.enumPackage;


import spharos.payment.global.config.CodeValue;

public enum PaymentStatus implements CodeValue {
    WAIT("0","결제대기"),
    DONE("1","DONE"),
    CANCEL("2","CANCELED"),

    EXPIRED("3","EXPIRED"),
    ABORTED("4","ABORTED");


    private final String code;
    private final String value;

    PaymentStatus(String code, String value) {
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
    public static PaymentStatus findByValue(String value) {
        for (PaymentStatus status : values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for code: " + value);
    }
}
