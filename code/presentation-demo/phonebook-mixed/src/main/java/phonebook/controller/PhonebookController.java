package phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phonebook.controller.wireType.PhoneEntryWireType;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;
import phonebook.repository.PhonebookEntryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PhonebookController {

    private PhonebookEntryRepository repository;

    @Autowired
    public PhonebookController(final PhonebookEntryRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/entries", method = GET, produces = "application/json")
    @ResponseBody
    public List<PhoneEntryWireType> entries() {
        return repository.all().map(this::entryToWireType).collect(Collectors.toList());
    }

    //TODO: move this out
    private PhoneEntryWireType entryToWireType(final Persisted<PhonebookEntry, String> e) {
        final PhonebookEntry entity = e.getEntity();
        return new PhoneEntryWireType(
                e.getId().getValue(),
                entity.getEmailAddress(),
                entity.getFirstName(),
                entity.getLastName()
        );
    }

    @RequestMapping(value = "/entries", method = POST, produces = "application/json")
    @ResponseBody
    public Persisted<PhonebookEntry, String> addEntry(final PhonebookEntry entry) {
        return repository.save(entry);
    }
}
