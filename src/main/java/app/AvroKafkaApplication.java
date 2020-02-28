package app;

import consumer.AvroConsumer;
import controller.AvroController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import producer.AvroProducer;

@SpringBootApplication
@Import({AvroConsumer.class, AvroProducer.class, AvroController.class})
public class AvroKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroKafkaApplication.class, args);
	}

}