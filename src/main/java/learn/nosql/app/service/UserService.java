package learn.nosql.app.service;

import learn.nosql.app.model.AverageAge;
import learn.nosql.app.model.User;
import learn.nosql.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    // To use the @RequiredArgsConstructor annotation.
    // Mark the fields you want to include in the constructor
    // as final or annotate them with @NonNull.
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

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

  // Find all users with an age greater than 30
  // Range Queries
  public List<User> findUsersGtAge(int age) {
    List<User> users = userRepository.findByAgeGreaterThan(30);
    return users;
    }
    
    // Add this method to UserService class
    public List<User> findUsersGtAgeSortedAndLimited(int age, int limit) {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("age").gt(age)),
            Aggregation.sort(Sort.by(Sort.Order.asc("name"))),
            Aggregation.limit(limit)
        );
    
        List<User> users = mongoTemplate.aggregate(aggregation, User.class, User.class).getMappedResults();
        return users;
    }

    // Add this method to UserService class
    public AverageAge calculateAverageAge() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("age").avg("age").as("averageAge"),
                Aggregation.project("averageAge").and("age").previousOperation()
        );

        AggregationResults<AverageAge> results = mongoTemplate.aggregate(aggregation, User.class, AverageAge.class);

        return results.getUniqueMappedResult();

    }
}