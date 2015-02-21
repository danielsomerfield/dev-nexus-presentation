package phonebook.controller;

import org.springframework.stereotype.Component;
import phonebook.controller.wireType.PhonebookEntryWireType;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;

@Component
public class PhonebookEntryWireTypeConverter {
    public PhonebookEntryWireTypeConverter() {
    }

    public PhonebookEntryWireType entryToWireType(final Persisted<PhonebookEntry, String> persisted) {
        return entryToWireType(persisted.getId().getValue(), persisted.getEntity());
    }

    public PhonebookEntryWireType entryToWireType(final PhonebookEntry entry) {
        return entryToWireType(null, entry);
    }

    public PhonebookEntryWireType entryToWireType(final String idValue, final PhonebookEntry entity) {
        return new PhonebookEntryWireType(
                idValue,
                entity.getLastName(),
                entity.getFirstName(),
                entity.getEmailAddress()
        );
    }

    public PhonebookEntry wireTypeToEntry(final PhonebookEntryWireType entry) {
        return PhonebookEntry.create(
                entry.getLastName(),
                entry.getFirstName(),
                entry.getEmailAddress()
        );
    }
}