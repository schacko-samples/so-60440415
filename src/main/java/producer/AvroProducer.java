package producer;

import java.util.function.Supplier;

import com.baeldung.schema.Employee;
import com.baeldung.schema.EmployeeKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Configuration
public class AvroProducer {

	private final EmitterProcessor<Message<?>> processor = EmitterProcessor.create();

	@Bean
	public Supplier<Flux<Message<?>>> employeeStream() {
		return () -> processor;
	}

	public void produceEmployeeDetails(int empId, String firstName, String lastName) {

		// creating employee details
		Employee employee = new Employee();
		employee.setId(empId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setDepartment("IT");
		employee.setDesignation("Engineer");

		// creating partition key for kafka topic
		EmployeeKey employeeKey = new EmployeeKey();
		employeeKey.setId(empId);
		employeeKey.setDepartmentName("IT");

		Message<Employee> message = MessageBuilder.withPayload(employee)
				.setHeader(KafkaHeaders.MESSAGE_KEY, employeeKey)
				.build();

		processor.onNext(message);
	}
}
