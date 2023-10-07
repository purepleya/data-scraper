package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationScraper {

    public List<Population> scrap(PopulationScrapYearMonth yearMonth) {
        return List.of();
    }
}
