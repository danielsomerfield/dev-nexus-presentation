package phonebook.domain;

import lombok.Data;

@Data(staticConstructor = "create")
public class PhonebookEntry {
    private final String lastName;
    private final String firstName;
    private final String emailAddress;
}
