package scratch.michelangelo;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class AccessManager {

    public boolean hasAccess(final String resourceName, final Principal principal,  final Permission.Action action) {
        throw new UnsupportedOperationException("NYI");
    }
}
