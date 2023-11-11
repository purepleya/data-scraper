package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScrapNextYearMonthFinder populationScrapNextYearMonthFinder;
    private final PopulationScraper scraper;
    private final PopulationDataRegister populationDataRegister;
    private final PopulationScrapLoggerBuilder loggerBuilder;

    public void run() throws InterruptedException {
        Optional<PopulationScrapYearMonth> yearMonthOptional;

        while ((yearMonthOptional = populationScrapNextYearMonthFinder.find()).isPresent()) {
            PopulationScrapYearMonth yearMonth = yearMonthOptional.get();
            PopulationScrapLoggerBuilder.PopulationScrapLogger logger = loggerBuilder.build(yearMonth);

            logger.start();

            List<PopulationScrapData> populationScrapData = scraper.scrap(yearMonth);

            populationDataRegister.save(populationScrapData);

            logger.end();

            Thread.sleep(10000L);
        }
    }



}
