package phonebook.controller;

import org.springframework.stereotype.Controller;
import phonebook.domain.PhonebookEntry;

import java.util.stream.Stream;

@Controller
public class PhonebookController {
    public Stream<PhonebookEntry> entries() {
        throw new UnsupportedOperationException(); // TODO
    }
}
