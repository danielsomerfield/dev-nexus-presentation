package scratch.michelangelo;

import lombok.Value;

@Value
public class Permission {
    private final Action action;
    private final String resource;

    public static enum Action {
        READ,
        WRITE
    }
}
