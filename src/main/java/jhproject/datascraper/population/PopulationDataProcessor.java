package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScrapParameterGenerator populationScrapParameterGenerator;
    private final PopulationScrapLoggerBuilder loggerBuilder;
    private final PopulationScraper populationScraper;
    private final PopulationDataRegister populationDataRegister;


    public void run() throws InterruptedException {

        Optional<PopulationScrapParameter> parameterOptional = populationScrapParameterGenerator.generate();

        while ((parameterOptional = populationScrapParameterGenerator.generate()).isPresent()) {
            PopulationScrapParameter parameter = parameterOptional.get();
            PopulationScrapLoggerBuilder.PopulationScrapLogger logger = loggerBuilder.build(parameter);
            logger.start();

            List<PopulationScrapData> populationScrapData = populationScraper.scrap(parameter);
            populationDataRegister.save(populationScrapData);

            logger.end();
        }
    }



}
