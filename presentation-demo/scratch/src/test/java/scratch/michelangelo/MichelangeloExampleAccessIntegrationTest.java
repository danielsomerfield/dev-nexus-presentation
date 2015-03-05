package scratch.michelangelo;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MichelangeloExampleAccessIntegrationTest {

    private Config config = new Config();
    private ResourceProvider resourceProvider;
    private Principal principal;

    @Before
    public void setup() {
        final SecurityContext securityContext = new SecurityContext(){
            @Override
            public Principal getPrincipal() {
                return principal;
            }
        };

        resourceProvider = config.resourceProvider(config.resourceManager(), securityContext);
    }

    @Test
    public void testAnonymousHasNoAccess() {
        asAnonymous();
        assertFalse(resourceProvider.getGoodies().isPresent());
    }

    @Test
    public void testThatManagersHaveResourceReadAccessToGoodies() {
        asManager();
        assertTrue(resourceProvider.getGoodies().isPresent());
    }

    private void asManager() {
        this.principal = Principal.from(Arrays.asList(Role.MANAGER));
    }

    private void asAnonymous() {
        this.principal = Principal.from(Arrays.asList(Role.ANONYMOUS));
    }


}
