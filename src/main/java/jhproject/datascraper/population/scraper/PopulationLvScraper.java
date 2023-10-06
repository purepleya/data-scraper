package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationLvScraper {

    private final PopulationPageScraper populationPageScraper;

    public List<Population> scrap(PopulationScrapParameter parameter) {
        List<Population> result  = new ArrayList<>(populationPageScraper.scrap(parameter));

        if (parameter.hasNextLv()) {
            List<PopulationScrapParameter> nextLvParameters = parameter.getNextLvParameters(result);
            nextLvParameters.stream()
                    .map(this::scrap)
                    .forEach(result::addAll);
        }
        return result;
    }
}
