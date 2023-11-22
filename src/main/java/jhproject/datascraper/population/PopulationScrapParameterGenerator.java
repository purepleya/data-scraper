package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationRepository;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PopulationScrapParameterGenerator {

//    private final PopulationRepository populationRepository;
    private final PopulationScrapLogRepository populationScrapLogRepository;


    public Optional<PopulationScrapParameter> generate() {
        Optional<PopulationScrapLog> lastLogOptional = populationScrapLogRepository.getLastLog();

        if (lastLogOptional.isEmpty()) {
            return Optional.of(PopulationScrapParameter.first());
        }

        if (allParametersScraped()) {
            String lastYearMonthString = "";
            PopulationScrapYearMonth lastYearMonth = PopulationScrapYearMonth.of(lastYearMonthString);

            return Optional.of(PopulationScrapParameter.firstOf(lastYearMonth.nextMonth()));
        }

        return Optional.empty();
    }

    public PopulationScrapParameterGenerator() {
        this.populationScrapLogRepository = null;
    }

    private boolean allParametersScraped() {
//        TODO: implement
        return true;
    }
}
