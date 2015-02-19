package phonebook.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import phonebook.domain.PhonebookEntry;

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

    public void save(final PhonebookEntry entry) {
        final BasicDBObject dbObject = new BasicDBObject()
                .append("lastName", entry.getLastName())
                .append("firstName", entry.getFirstName())
                .append("emailAddress", entry.getEmailAddress());

        db.getCollection("phonebook-entry").save(dbObject);
    }

    public Stream<PhonebookEntry> all() {
        return stream(spliteratorUnknownSize(db.getCollection("phonebook-entry").find().iterator(), Spliterator.IMMUTABLE), false)
                .map(this::entry);
    }

    private PhonebookEntry entry(final DBObject object) {
        return PhonebookEntry.create(
                (String)object.get("lastName"),
                (String)object.get("firstName"),
                (String)object.get("emailAddress")
        );
    }
}
