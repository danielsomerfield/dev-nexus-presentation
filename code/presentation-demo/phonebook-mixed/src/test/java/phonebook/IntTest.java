package phonebook;

import org.junit.runner.RunWith;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, WebConfiguration.class, TestConfiguration.class})
@EnableWebMvc
@WebAppConfiguration
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = {Controller.class, Component.class})})
public abstract class IntTest {
}
