package phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phonebook.controller.wireType.PhonebookEntryWireType;
import phonebook.controller.wireType.PhonebookEntryWireTypeConverter;
import phonebook.domain.PhonebookEntry;
import phonebook.repository.Persisted;
import phonebook.repository.PhonebookEntryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/services")
public class PhonebookController {

    private final PhonebookEntryWireTypeConverter phonebookEntryWireTypeConverter;
    private PhonebookEntryRepository repository;

    @Autowired
    public PhonebookController(final PhonebookEntryRepository repository, final PhonebookEntryWireTypeConverter phonebookEntryWireTypeConverter) {
        this.repository = repository;
        this.phonebookEntryWireTypeConverter = phonebookEntryWireTypeConverter;
    }

    @RequestMapping(value = "/entries", method = GET, produces = "application/json")
    @ResponseBody
    public List<PhonebookEntryWireType> entries() {
        return repository.all().map(phonebookEntryWireTypeConverter::entryToWireType).collect(Collectors.toList());
    }

    @RequestMapping(value = "/entries", method = POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public PhonebookEntryWireType addEntry(@RequestBody final PhonebookEntryWireType wireType) {
        //TODO convert this to a save
        final PhonebookEntry entry = phonebookEntryWireTypeConverter.wireTypeToEntry(wireType);
        return phonebookEntryWireTypeConverter.entryToWireType(repository.save(entry));
    }
}
