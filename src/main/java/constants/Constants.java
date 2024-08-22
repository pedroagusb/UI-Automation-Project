package constants;

import java.time.Duration;

public class Constants {
    private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(10);
    public static final int WAIT = 5;

    public static Duration getExplicitWait() {
        return EXPLICIT_WAIT;
    }
    public static int getWait() {
        return WAIT;
    }
}
