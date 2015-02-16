package phonebook.controllers;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PingController {

    @RequestMapping(value="/ping", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public PingResult ping() {
        return new PingResult();
    }

    @Data
    private static class PingResult {
        private final String result = "ok";
    }
}
