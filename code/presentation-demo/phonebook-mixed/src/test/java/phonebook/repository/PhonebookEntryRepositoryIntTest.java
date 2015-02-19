package phonebook.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import phonebook.IntTest;
import phonebook.domain.PhonebookEntry;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhonebookEntryRepositoryIntTest extends IntTest {

    @Autowired
    private PhonebookEntryRepository phonebookEntryRepository;

    @Autowired
    private DB db;

    @Before
    public void setup() {
        db.dropDatabase();
    }

    @Test
    public void testCreateEntry() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@example.com");
        phonebookEntryRepository.save(entry);
        assertThat(db.getCollection("phonebook-entry").count(), is(1l));
        final DBObject phoneEntry = db.getCollection("phonebook-entry").findOne();
        assertThat(phoneEntry.get("lastName"), is("last-name"));
        assertThat(phoneEntry.get("firstName"), is("first-name"));
        assertThat(phoneEntry.get("emailAddress"), is("email@example.com"));
    }


}