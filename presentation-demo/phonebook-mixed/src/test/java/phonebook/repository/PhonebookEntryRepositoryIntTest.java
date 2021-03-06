package phonebook.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import phonebook.IntTest;
import phonebook.domain.PhonebookEntry;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
        Persisted<PhonebookEntry, String> persistedEntry = phonebookEntryRepository.save(entry);
        assertThat(db.getCollection("phonebook-entry").count(), is(1l));
        final DBObject phoneEntry = db.getCollection("phonebook-entry").findOne();
        assertThat(phoneEntry.get("lastName"), is("last-name"));
        assertThat(phoneEntry.get("firstName"), is("first-name"));
        assertThat(phoneEntry.get("emailAddress"), is("email@example.com"));
        assertThat(phoneEntry.get("_id"), is(persistedEntry.getId().getValue()));
    }

    @Test
    public void testAll() {
        final DBObject entry1 = saveNewEntry();
        final DBObject entry2 = saveNewEntry();

        List<Persisted<PhonebookEntry, String>> entries = phonebookEntryRepository.all().collect(Collectors.toList());
        assertThat(entries.size(), is(2));

        hasMatchingEntry(entry1, entries);
        hasMatchingEntry(entry2, entries);
    }

    private boolean hasMatchingEntry(final DBObject entry1, final List<Persisted<PhonebookEntry, String>> entries) {
        return entries.stream().filter(matches(entry1)).findFirst().isPresent();
    }

    private Predicate<Persisted<PhonebookEntry, String>> matches(DBObject object) {
        return e -> e.getEntity().getEmailAddress() == object.get("emailAddress")
                && e.getEntity().getFirstName() == object.get("firstName")
                && e.getEntity().getLastName() == object.get("lastName")
                && e.getId() == object.get("_id");
    }


    private BasicDBObject saveNewEntry() {
        final String uuid = UUID.randomUUID().toString();
        final BasicDBObject entry = new BasicDBObject()
                .append("lastName", format("lastname-%s", uuid))
                .append("firstName", format("firstname-%s", uuid))
                .append("emailAddress", format("email-%s@example.com", uuid));
        db.getCollection("phonebook-entry").save(entry);
        return entry;
    }


}