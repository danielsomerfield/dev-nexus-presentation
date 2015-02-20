package phonebook.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServiceClient {

    public static ServiceClient client() {
        return new ServiceClient();
    }

    @SneakyThrows(IOException.class)
    public Entry addEntry(final Entry entry) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            final CloseableHttpResponse response = client.execute(RequestBuilder.create("POST").setUri(getURI("/entry")).setEntity(EntityBuilder.create()
                    .setText(new ObjectMapper().writeValueAsString(entry))
                    .setContentType(ContentType.APPLICATION_JSON)
                    .build()).build());
            assertThat(response.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
            return toEntry(response.getEntity().getContent());
        }
    }

    @SneakyThrows
    private Entry toEntry(final InputStream content) {
        return new ObjectMapper().readValue(content, Entry.class);
    }

    private URI getURI(final String path) {
        return URI.create(format("http://localhost:8080/services/%s", path));
    }

    @Data
    @AllArgsConstructor()
    public static class Entry {
        private final String id;
        private final String lastName;
        private final String firstName;
        private final String emailAddress;
    }
}
