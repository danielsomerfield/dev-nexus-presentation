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
import java.util.stream.Stream;

import static java.lang.String.format;
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

    @Test
    public void testAll() {
        final BasicDBObject entry1 = createEntry();
        db.getCollection("phonebook-entry").save(entry1);
        final DBObject entry2 = createEntry();
        db.getCollection("phonebook-entry").save(entry2);

        List<PhonebookEntry> entries = phonebookEntryRepository.all().collect(Collectors.toList());
        assertThat(entries.size(), is(2));

        entries.stream().filter(matches(entry1)).findFirst().isPresent();
        entries.stream().filter(matches(entry2)).findFirst().isPresent();
    }

    private Predicate<PhonebookEntry> matches(DBObject object) {
        return e -> e.getEmailAddress() == object.get("emailAddress")
                && e.getFirstName() == object.get("firstName")
                && e.getLastName() == object.get("lastName");
    }


    private BasicDBObject createEntry() {
        final String uuid = UUID.randomUUID().toString();
        return new BasicDBObject()
                .append("lastName", format("lastname-%s", uuid))
                .append("firstName", format("firstname-%s", uuid))
                .append("emailAddress", format("email-%s@example.com", uuid));
    }


}