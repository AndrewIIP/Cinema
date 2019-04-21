package model.util;

import java.util.Arrays;

public enum Role {
    GUEST("guest"),
    USER("user"),
    ADMIN("admin");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getString() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Role contains(String value) {
        return Arrays.stream(Role.values()).filter(a -> a.getString().equals(value)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
