package jhproject.datascraper;

import jhproject.datascraper.population.PopulationDataProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataScraperApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(DataScraperApplication.class, args);

		PopulationDataProcessor populationDataProcessor = context.getBean(PopulationDataProcessor.class);

		try {
			populationDataProcessor.run();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
