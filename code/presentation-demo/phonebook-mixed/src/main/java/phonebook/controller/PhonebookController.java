package phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.PhonebookEntryRepository;

import java.util.stream.Stream;

@Controller
public class PhonebookController {

    private PhonebookEntryRepository repository;

    @Autowired
    public PhonebookController(final PhonebookEntryRepository repository) {
        this.repository = repository;
    }

    public Stream<PhonebookEntry> entries() {
        return repository.all();
    }
}
