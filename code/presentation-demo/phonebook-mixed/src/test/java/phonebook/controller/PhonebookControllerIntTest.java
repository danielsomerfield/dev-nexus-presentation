package phonebook.controller;

import com.mongodb.DB;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import phonebook.IntTest;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;
import phonebook.repository.PhonebookEntryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        final List<Persisted<PhonebookEntry, String>> entries = phonebookController.entries();
        assertThat(entries.size(), is(1));
        assertThat(entries.get(0).getEntity(), is(entry));
    }

    @Test
    public void testAddEntry() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@example.com");
        final Persisted<PhonebookEntry, String> persisted = phonebookController.addEntry(entry);
        assertThat(persisted.getEntity(), is(entry));

        final List<Persisted<PhonebookEntry, String>> entries = phonebookEntryRepository.all().collect(Collectors.toList());
        assertThat(entries.size(), is(1));
        assertThat(entries.get(0).getEntity(), is(entry));
        assertThat(entries.get(0).getId(), is(persisted.getId()));
    }

}
