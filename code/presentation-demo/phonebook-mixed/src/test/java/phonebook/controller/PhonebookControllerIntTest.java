package phonebook.controller;

import com.mongodb.DB;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import phonebook.IntTest;
import phonebook.domain.PhonebookEntry;
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
        List<PhonebookEntry> entries = phonebookController.entries().collect(Collectors.toList());
        assertThat(entries.size(), is(1));
        assertThat(entries.get(0), is(entry));
    }

}
