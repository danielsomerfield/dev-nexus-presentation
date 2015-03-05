package phonebook.repository;

import lombok.Data;
import lombok.Value;

@Value
public class Persisted<T, K> {

    private final T entity;
    private final Id<K> id;

    @Data
    public static class Id<V> {
        private final V value;
    }
}
