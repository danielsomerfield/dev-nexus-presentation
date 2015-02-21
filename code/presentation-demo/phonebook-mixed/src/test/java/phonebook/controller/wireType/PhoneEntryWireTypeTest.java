package phonebook.controller.wireType;

import org.junit.Test;
import phonebook.controller.PhonebookEntryWireTypeConverter;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhoneEntryWireTypeTest {

    @Test
    public void testConversionWithoutId() {
        final PhoneEntryWireType wireType = new PhoneEntryWireType(null, "last-name", "first-name", "email@address.com");
        final PhonebookEntryWireTypeConverter controller = new PhonebookEntryWireTypeConverter();
        final PhonebookEntry entry = controller.wireTypeToEntry(wireType);
        assertThat(controller.entryToWireType(entry), is(wireType));
    }

    @Test
    public void testEntryToWireType() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@address.com");
        final PhonebookEntryWireTypeConverter controller = new PhonebookEntryWireTypeConverter();
        final PhoneEntryWireType wireType = controller.entryToWireType(entry);
        assertThat(controller.wireTypeToEntry(wireType), is(entry));
    }


    @Test
    public void testPersistedToWireType() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@address.com");
        final Persisted<PhonebookEntry, String> persisted = new Persisted<>(entry, new Persisted.Id<>("an-id"));
        final PhonebookEntryWireTypeConverter controller = new PhonebookEntryWireTypeConverter();
        final PhoneEntryWireType wireType = controller.entryToWireType(persisted);
        assertThat(wireType.getId(), is("an-id"));
        assertThat(controller.wireTypeToEntry(wireType), is(entry));

    }

}