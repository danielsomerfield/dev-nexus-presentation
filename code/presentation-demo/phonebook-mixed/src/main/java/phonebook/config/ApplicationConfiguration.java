package phonebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phonebook.controller.PhonebookController;
import phonebook.repository.PhonebookEntryRepository;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PhonebookController phonebookController() {
        return new PhonebookController();
    }

    @Bean
    public PhonebookEntryRepository phonebookEntryRepository() {
        return new PhonebookEntryRepository();
    }



}
