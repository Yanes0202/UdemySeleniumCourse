package adrian.com.commons.enums;

import lombok.Getter;

@Getter
public enum Environment {
    DEV("dev"), UAT("uat"), PROD("prod");

    private final String value;

    Environment(String value) {
        this.value = value;
    }
}
