package learn.nosql.app;

import learn.nosql.app.model.User;
import learn.nosql.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			if (repository.count() == 0) {
				// Insert sample users only if the database is empty
				if (repository.count() == 0) {
					repository.save(User.builder()
							.name("Alice Johnson")
							.email("alice@example.com")
							.age(30)  // Example age
							.build());

					repository.save(User.builder()
							.name("Bob Smith")
							.email("bob@example.com")
							.age(35)  // Example age
							.build());

					repository.save(User.builder()
							.name("Charlie Brown")
							.email("charlie@example.com")
							.age(40)  // Example age
							.build());

					repository.save(User.builder()
							.name("Charlotte Cooper")
							.email("charlotte@example.com")
							.age(28)  // Example age
							.build());

					repository.save(User.builder()
							.name("Charles Darwin")
							.email("charles@example.com")
							.age(55)  // Example age
							.build());
				}

			}
		};
		
	}
}