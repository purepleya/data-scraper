package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PopulationScraper {

    private final PopulationRegSeCdScraper populationRegSeCdScraper;

    public List<Population> scrap(PopulationScrapYearMonth yearMonth) {
        if (Objects.isNull(yearMonth) || !yearMonth.isValid()) {
            return List.of();
        }

        PopulationScrapParameter parameter = PopulationScrapParameter.firstOf(yearMonth);
        return populationRegSeCdScraper.scrap(parameter);
    }
}
