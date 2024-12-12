package devtitans.antoshchuk.healthcare.controllers.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @GetMapping("test/")
    public String getUsers() {
        return "Hello World";
    }
}
