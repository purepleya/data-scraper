package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PopulationRegSeCdScraper {

    private final PopulationLvScraper populationLvScraper;

    public List<PopulationScrapData> scrap(PopulationScrapParameter parameter) {
        if (Objects.isNull(parameter)) {
            return List.of();
        }

        List<PopulationScrapData> result = new ArrayList<>(populationLvScraper.scrap(parameter));

        Optional<PopulationScrapParameter> nextRegSeCdParameterOptional = parameter.getNextRegSeCdParameter();
        nextRegSeCdParameterOptional.ifPresent(nextParameter -> result.addAll(scrap(nextParameter)));

        return result;
    }

}
