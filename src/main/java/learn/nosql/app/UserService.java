package learn.nosql.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    // To use the @RequiredArgsConstructor annotation.
    // Mark the fields you want to include in the constructor
    // as final or annotate them with @NonNull.
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByName(String name) {
        User user = userRepository.findByNameUsingCustomQueryLike(name);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    public User updateUser(String id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
