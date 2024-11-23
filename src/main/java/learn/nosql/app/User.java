package learn.nosql.app;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;

    // Constructor to easily create new User objects
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
