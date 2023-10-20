package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PopulationScrapNextYearMonthFinder {

    private final PopulationScrapLogRepository populationScrapLogRepository;

    public Optional<PopulationScrapYearMonth> find() {
        Optional<PopulationScrapLog> lastLogOptional = populationScrapLogRepository.getLastLog();

        if (lastLogOptional.isEmpty()) {
            return Optional.of(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
        }

        PopulationScrapLog lastLog = lastLogOptional.get();
        PopulationScrapYearMonth nextYearMonth = PopulationScrapYearMonth.of(LocalDate.of(lastLog.getYyyy(), lastLog.getMm(), 1)).nextMonth();

        if (nextYearMonth.isValid()) {
            return Optional.of(nextYearMonth);
        } else {
            return Optional.empty();
        }
    }

}
