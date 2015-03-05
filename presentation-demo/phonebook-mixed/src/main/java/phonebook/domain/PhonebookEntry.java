package phonebook.domain;

import lombok.Value;

@Value(staticConstructor = "create")
public class PhonebookEntry {
    private final String lastName;
    private final String firstName;
    private final String emailAddress;
}
