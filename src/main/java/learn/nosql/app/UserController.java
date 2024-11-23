package learn.nosql.app;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/")
    public User getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }
}
