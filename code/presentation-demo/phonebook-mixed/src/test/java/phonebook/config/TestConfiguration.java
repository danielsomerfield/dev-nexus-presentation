package phonebook.config;

import com.mongodb.DB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public DB db() {
        throw new UnsupportedOperationException("NYI");
    }
}
