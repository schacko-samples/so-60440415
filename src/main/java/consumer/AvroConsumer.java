package consumer;

import java.util.function.Consumer;

import com.baeldung.schema.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvroConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvroConsumer.class);

	@Bean
	public Consumer<Employee> consumeEmployeeDetails() {
		return employee -> {
			LOGGER.info("Let's process employee details: {}", employee);
		};
	}
}
