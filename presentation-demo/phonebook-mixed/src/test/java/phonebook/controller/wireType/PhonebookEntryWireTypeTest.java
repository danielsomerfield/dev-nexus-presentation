package phonebook.controller.wireType;

import org.junit.Test;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhonebookEntryWireTypeTest {

    @Test
    public void testConversionWithoutId() {
        final PhonebookEntryWireType wireType = new PhonebookEntryWireType(null, "last-name", "first-name", "email@address.com");
        final PhonebookEntryWireTypeConverter controller = new PhonebookEntryWireTypeConverter();
        final PhonebookEntry entry = controller.wireTypeToEntry(wireType);
        assertThat(controller.entryToWireType(entry), is(wireType));
    }

    @Test
    public void testEntryToWireType() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@address.com");
        final PhonebookEntryWireTypeConverter controller = new PhonebookEntryWireTypeConverter();
        final PhonebookEntryWireType wireType = controller.entryToWireType(entry);
        assertThat(controller.wireTypeToEntry(wireType), is(entry));
    }


    @Test
    public void testPersistedToWireType() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@address.com");
        final Persisted<PhonebookEntry, String> persisted = new Persisted<>(entry, new Persisted.Id<>("an-id"));
        final PhonebookEntryWireTypeConverter controller = new PhonebookEntryWireTypeConverter();
        final PhonebookEntryWireType wireType = controller.entryToWireType(persisted);
        assertThat(wireType.getId(), is("an-id"));
        assertThat(controller.wireTypeToEntry(wireType), is(entry));

    }

}