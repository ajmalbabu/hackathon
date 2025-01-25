
package hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("hackathon.repository.jpa")
@EnableElasticsearchRepositories("hackathon.repository.elasticsearch")
public class TodoApplication {

	public static void main(String[] args) {
		System.out.println("Starting app...");

		SpringApplication.run(TodoApplication.class, args);
	}

}
