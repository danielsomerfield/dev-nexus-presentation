package phonebook.controller.wireType;

import lombok.Data;

@Data
public class PhoneEntryWireType {
    private final String id;
    private final String lastName;
    private final String firstName;
    private final String emailAddress;
}
