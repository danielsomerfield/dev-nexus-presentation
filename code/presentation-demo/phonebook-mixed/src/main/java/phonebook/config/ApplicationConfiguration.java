package phonebook.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Configuration
@Import({SecurityConfiguration.class, TestSecurityConfiguration.class})
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
