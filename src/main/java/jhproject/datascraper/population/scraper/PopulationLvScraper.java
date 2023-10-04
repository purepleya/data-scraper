package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationLvScraper {

    private final PopulationPageScraper pupulationPageScraper;

    public List<Population> scrap(PopulationScrapParameter parameter) {
        return pupulationPageScraper.scrap(parameter);
    }
}
