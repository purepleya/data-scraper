package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

        PopulationScrapParameter lastParameter = new PopulationScrapParameter(lastLogOptional.get());
        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(List.of());

        return nextParameterOptional;
    }


}
