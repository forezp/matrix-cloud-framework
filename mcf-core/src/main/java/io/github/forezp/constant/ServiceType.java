package io.github.forezp.constant;


public enum ServiceType {
    SERVICE(SwrConstants.SERVICE),
    GATEWAY(SwrConstants.GATEWAY),
    CONSOLE(SwrConstants.CONSOLE),
    TEST(SwrConstants.TEST);

    private String value;

    ServiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ServiceType fromString(String value) {
        for (ServiceType type : ServiceType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException("No matched type with value=" + value);
    }

    @Override
    public String toString() {
        return value;
    }
}