package phonebook.controller;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import phonebook.service.LoginService;

import java.security.Principal;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }


    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public LoginResult loginException(@RequestParam String username, String password) {
        try {
            Principal principal = loginService.login(username, password);
            addPrincipalToSession(principal);
            return new LoginResult("Logged in.", true);
        } catch (LoginService.LoginFailedException e) {
            switch (e.getLoginFailure()) {
                case ACCOUNT_LOCKED:
                    return new LoginResult("Your account has been locked. Please call the help desk.", false);
                case INVALID_CREDENTIALS:
                    return new LoginResult("Your credentials are invalid.", false);
                default:
                    throw new RuntimeException("");
            }
        }
    }

    private void addPrincipalToSession(final Principal principal) {
        throw new UnsupportedOperationException("NYI");
    }

    @Value
    private class LoginResult {
        private final String message;
        private final boolean success;
    }
}
