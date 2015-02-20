package phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;
import phonebook.repository.PhonebookEntryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PhonebookController {

    private PhonebookEntryRepository repository;

    @Autowired
    public PhonebookController(final PhonebookEntryRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "entries", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Persisted<PhonebookEntry, String>> entries() {
        return repository.all().collect(Collectors.toList());
    }
}
