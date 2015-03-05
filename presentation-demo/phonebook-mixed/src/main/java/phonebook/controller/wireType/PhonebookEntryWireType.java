package phonebook.controller.wireType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PhonebookEntryWireType {
    private final String id;
    private final String lastName;
    private final String firstName;
    private final String emailAddress;

    @JsonCreator
    public PhonebookEntryWireType(
            @JsonProperty("id") final String id,
            @JsonProperty("lastName") final String lastName,
            @JsonProperty("firstName") final String firstName,
            @JsonProperty("emailAddress") final String emailAddress) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
    }
}
