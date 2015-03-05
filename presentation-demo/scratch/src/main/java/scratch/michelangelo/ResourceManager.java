package scratch.michelangelo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

import static scratch.michelangelo.Permission.Action.READ;

@AllArgsConstructor
public class ResourceManager {

    public Optional<Resource> getResource(final String resourceName, final Principal principal) {
        return hasAccess(resourceName, principal, READ) ? Optional.of(resource(resourceName)) : Optional.empty();
    }

    private boolean hasAccess(final String resourceName, final Principal principal, final Permission.Action action) {
        return principal.hasPermission(resourceName, action);
    }

    private Resource resource(final String resourceName) {
        return new Resource(resourceName);

    }

    @Data
    public static class Resource {
        private final String resourceName;
    }
}
