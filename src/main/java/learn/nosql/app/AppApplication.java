package learn.nosql.app;

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
				repository.save(new User("Alice Johnson", "alice@example.com"));
				repository.save(new User("Bob Smith", "bob@example.com"));
				repository.save(new User("Charlie Brown", "charlie@example.com"));
			}
		};
	}
}