package phonebook.controller;

import org.springframework.stereotype.Component;
import phonebook.controller.wireType.PhoneEntryWireType;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;

@Component
public class PhonebookEntryWireTypeConverter {
    public PhonebookEntryWireTypeConverter() {
    }

    public PhoneEntryWireType entryToWireType(final Persisted<PhonebookEntry, String> persisted) {
        return entryToWireType(persisted.getId().getValue(), persisted.getEntity());
    }

    public PhoneEntryWireType entryToWireType(final PhonebookEntry entry) {
        return entryToWireType(null, entry);
    }

    public PhoneEntryWireType entryToWireType(final String idValue, final PhonebookEntry entity) {
        return new PhoneEntryWireType(
                idValue,
                entity.getLastName(),
                entity.getFirstName(),
                entity.getEmailAddress()
        );
    }

    public PhonebookEntry wireTypeToEntry(final PhoneEntryWireType entry) {
        return PhonebookEntry.create(
                entry.getLastName(),
                entry.getFirstName(),
                entry.getEmailAddress()
        );
    }
}