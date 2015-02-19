package phonebook.controller;

import com.mongodb.DB;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import phonebook.IntTest;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.PhonebookEntryRepository;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore("Not yet implemented.")
public class PhonebookControllerIntTest extends IntTest {

    @Autowired
    private PhonebookController phonebookController;

    @Autowired
    private PhonebookEntryRepository phonebookEntryRepository;

    @Autowired
    private DB db;

    @Before
    public void setup() {
        db.dropDatabase();
    }

    @Test
    public void testEntryList() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@example.com");
        phonebookEntryRepository.save(entry);
        Stream<PhonebookEntry> entries = phonebookController.entries();
        assertThat(entries.count(), is(1));
        assertThat(entries.findFirst(), is(entry));
    }

}
