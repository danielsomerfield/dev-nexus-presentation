package scratch.michelangelo;

public class Config {

    public ResourceProvider resourceProvider(ResourceManager resourceManager, SecurityContext securityContext) {
        return new ResourceProvider(resourceManager, securityContext);
    }

    public ResourceManager resourceManager() {
        return new ResourceManager();
    }
}
