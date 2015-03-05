package phonebook.config;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    private Fongo fongo = new Fongo("main-mongo-instance");

    @Bean
    public DB db() {
        return fongo.getDB("test-fongo");
    }
}
