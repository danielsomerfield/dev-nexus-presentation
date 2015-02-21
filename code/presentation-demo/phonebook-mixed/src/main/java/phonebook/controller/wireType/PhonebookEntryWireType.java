package phonebook.controller.wireType;

import lombok.Data;

@Data
public class PhonebookEntryWireType {
    private final String id;
    private final String lastName;
    private final String firstName;
    private final String emailAddress;
}
