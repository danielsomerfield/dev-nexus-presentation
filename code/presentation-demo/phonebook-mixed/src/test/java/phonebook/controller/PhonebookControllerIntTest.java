package phonebook.controller;

import com.mongodb.DB;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import phonebook.IntTest;
import phonebook.controller.wireType.PhoneEntryWireType;
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
        final List<PhoneEntryWireType> entries = phonebookController.entries();
        assertThat(entries.size(), is(1));
        assertThat(entries.get(0), isEquivalentTo(entry));
    }

    private Matcher<PhoneEntryWireType> isEquivalentTo(final PhonebookEntry entry) {
        return new CustomMatcher<PhoneEntryWireType>("is equivalent to") {
            @Override
            public boolean matches(final Object item) {
                if (item instanceof PhoneEntryWireType) {
                    PhoneEntryWireType wireType = ((PhoneEntryWireType) item);
                    return entry.getEmailAddress().equals(wireType.getEmailAddress())
                            && entry.getFirstName().equals(wireType.getFirstName())
                            && entry.getLastName().equals(wireType.getLastName()
                    );
                }
                throw new UnsupportedOperationException("NYI");
            }
        };
    }

    @Test
    public void testAddEntry() {
        final PhoneEntryWireType entryWireType = new PhoneEntryWireType(null, "last-name", "first-name", "email@example.com");
        final Persisted<PhonebookEntry, String> persisted = phonebookController.addEntry(entryWireType);
        assertThat(entryWireType, isEquivalentTo(persisted.getEntity()));

        final List<Persisted<PhonebookEntry, String>> entries = phonebookEntryRepository.all().collect(Collectors.toList());
        assertThat(entries.size(), is(1));
        assertThat(entryWireType, isEquivalentTo(entries.get(0).getEntity()));
        assertThat(entries.get(0).getId(), is(persisted.getId()));
    }

}
