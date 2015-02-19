package phonebook.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import phonebook.domain.PhonebookEntry;

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
}
