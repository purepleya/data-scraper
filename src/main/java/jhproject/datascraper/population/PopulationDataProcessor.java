package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScrapParameterGenerator populationScrapParameterGenerator;
    private final PopulationScrapLoggerBuilder loggerBuilder;
    private final PopulationScraper populationScraper;
    private final PopulationDataRegister populationDataRegister;


    public void run() throws InterruptedException {

        Optional<PopulationScrapParameter> parameterOptional = populationScrapParameterGenerator.generate();

        while ((parameterOptional = populationScrapParameterGenerator.generate()).isPresent()) {
            runSingleCycle(parameterOptional.get());
            TimeUnit.SECONDS.sleep(3);
        }
    }

    @Transactional
    private void runSingleCycle(PopulationScrapParameter parameter) {
        PopulationScrapLoggerBuilder.PopulationScrapLogger logger = loggerBuilder.build(parameter);
        logger.start();

        List<PopulationScrapData> populationScrapData = populationScraper.scrap(parameter);
        populationDataRegister.save(populationScrapData);

        logger.end();
    }



}
