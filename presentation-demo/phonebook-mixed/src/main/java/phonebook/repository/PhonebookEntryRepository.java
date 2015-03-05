package phonebook.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted.Id;

import java.util.Spliterator;
import java.util.stream.Stream;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

@Repository
public class PhonebookEntryRepository {

    private DB db;

    @Autowired
    public PhonebookEntryRepository(final DB db) {
        this.db = db;
    }

    public Persisted<PhonebookEntry, String> save(final PhonebookEntry entry) {
        final BasicDBObject dbObject = new BasicDBObject()
                .append("lastName", entry.getLastName())
                .append("firstName", entry.getFirstName())
                .append("emailAddress", entry.getEmailAddress());

        db.getCollection("phonebook-entry").insert(dbObject);
        return new Persisted<>(entry, new Id<>(dbObject.get("_id").toString()));
    }

    public Stream<Persisted<PhonebookEntry, String>> all() {
        return stream(spliteratorUnknownSize(db.getCollection("phonebook-entry").find().iterator(), Spliterator.IMMUTABLE), false)
                .map(this::entry);
    }

    private Persisted<PhonebookEntry, String> entry(final DBObject object) {
        final PhonebookEntry phonebookEntry = PhonebookEntry.create(
                (String) object.get("lastName"),
                (String) object.get("firstName"),
                (String) object.get("emailAddress")
        );
        return new Persisted<>(phonebookEntry, new Id<>(object.get("_id").toString()));
    }
}
