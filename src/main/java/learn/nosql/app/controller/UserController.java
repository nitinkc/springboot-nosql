package learn.nosql.app.controller;

import learn.nosql.app.model.AverageAge;
import learn.nosql.app.model.User;
import learn.nosql.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/average-age")
    public ResponseEntity<AverageAge> calculateAverageAge() {
        AverageAge averageAge = userService.calculateAverageAge();
        if (averageAge != null) {
            return ResponseEntity.ok(averageAge);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/age-greater-than/{age}/sorted-and-limited/{limit}")
    public ResponseEntity<List<User>> findUsersGtAgeSortedAndLimited(@PathVariable int age, @PathVariable int limit) {
        List<User> users = userService.findUsersGtAgeSortedAndLimited(age, limit);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
