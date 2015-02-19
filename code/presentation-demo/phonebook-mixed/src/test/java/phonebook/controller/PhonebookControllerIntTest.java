package phonebook.controller;

import com.mongodb.DB;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import phonebook.config.ApplicationConfiguration;
import phonebook.config.TestConfiguration;
import phonebook.config.WebConfiguration;
import phonebook.domain.PhonebookEntry;
import phonebook.domain.PhonebookEntryList;
import phonebook.repository.PhonebookEntryRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore("Not yet implemented.")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, WebConfiguration.class, TestConfiguration.class})
@EnableWebMvc
@WebAppConfiguration
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = {Controller.class, Component.class})})
public class PhonebookControllerIntTest {

    @Autowired
    private PhonebookController phonebookController;

    @Autowired
    private PhonebookEntryRepository phonebookEntryRepository;

    @Autowired
    private DB db;

    @Before
    public void setup() {
        db.dropDatabase();
    }

    @Test
    public void testEntryList() {
        final PhonebookEntry entry = PhonebookEntry.create("last-name", "first-name", "email@example.com");
        phonebookEntryRepository.save(entry);
        PhonebookEntryList entries = phonebookController.entries();
        assertThat(entries.count(), is(1));
        assertThat(entries.next(), is(entry));
    }

}
