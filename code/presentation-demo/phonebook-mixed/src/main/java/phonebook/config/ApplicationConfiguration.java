package phonebook.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DB db(MongoClient client) {
        return client.getDB("phonebook-application");
    }

    @Bean
    @SneakyThrows
    public MongoClient client() {
        return new MongoClient("localhost");
    }
}
