package jhproject.datascraper.population.scraper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PublicDataPopulationGetParameterBuilder {

    @Value("${jhproject.public-data.service-key}")
    public String serviceKey;


}
