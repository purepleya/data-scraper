package jhproject.datascraper.population;

import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PopulationScrapNextYearMonthFinder {

    private final PopulationScrapLogRepository populationScrapLogRepository;

    public Optional<PopulationScrapYearMonth> find() {
        return Optional.of(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
    }

}
