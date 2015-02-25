package phonebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RequestMatcher csrfMatcher;

    public SecurityConfiguration() {
        super();
    }

    @Autowired
    protected void configureAuth(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("admin").password("admin").roles("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().requireCsrfProtectionMatcher(csrfMatcher);
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(GET, "/").permitAll()
                .antMatchers(GET, "/ping").permitAll()
                .antMatchers(GET, "/static/**").permitAll()
                .antMatchers(GET, "/services/**").permitAll()
                .antMatchers(POST, "/services/**").permitAll() //TODO: enable auth
                .antMatchers(POST, "/j_spring_security_check").permitAll()
                .anyRequest().denyAll()
        ;
    }

    @Bean
    public RequestMatcher csrfMatcher() {
        return request -> request.getMethod().equals("POST");
    }

}
