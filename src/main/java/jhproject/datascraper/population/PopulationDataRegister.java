package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.repository.PopulationRepository;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PopulationDataRegister {

    private final PopulationRepository populationRepository;

    @Transactional
    public void save(List<PopulationScrapData> populationScrapData) {
        List<Population> entities = populationScrapData.stream().map(PopulationScrapData::toEntity).toList();
        populationRepository.saveAll(entities);
    }


}
