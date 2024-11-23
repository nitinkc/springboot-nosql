package learn.nosql.app;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    // Custom query to find a user by email
    @Query("{ 'name' : ?0 }")
    User findByNameUsingCustomQuery(String name);

    // Using regex to simulate LIKE
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    User findByNameUsingCustomQueryLike(String name);

}