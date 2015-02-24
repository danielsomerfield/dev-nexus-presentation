package phonebook.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Profile("test")
@Slf4j
public class TestSecurityConfiguration {

    @Bean
    private RequestMatcher csrfMatcher() {
        log.warn("**** Running in test mode. CSRF protection is disabled for X-Test header");
        return request -> request.getMethod().equals("POST") && !request.getHeader("X-Test").equals("true");
    }
}
