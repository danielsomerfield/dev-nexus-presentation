package scratch.michelangelo;

import lombok.AllArgsConstructor;

import java.util.Optional;


@AllArgsConstructor
public class ResourceProvider {

    private final ResourceManager resourceManager;
    private final SecurityContext securityContext;

    public Optional<ResourceManager.Resource> getGoodies() {
        return resourceManager.getResource("goodies", securityContext.getPrincipal());
    }

//    public void setGoodies(ResourceManager.Resource resource) {
//        return resourceManager.setResource("goodies", resource, securityContext.getPrincipal());
//    }
}
