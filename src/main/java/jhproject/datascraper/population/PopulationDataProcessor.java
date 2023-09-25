package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScraper populationScraper;
    private final PopulationDataRegister populationDataService;

    public void run() {
//        최초일(2022.10) 부터 지난달 까지 수집
        PopulationScrapYearMonth yearMonth = PopulationScrapYearMonth.FIRST_YEAR_MONTH;
        while (!yearMonth.isLastMonth()) {
            run(yearMonth);
            yearMonth = yearMonth.nextMonth();
        }
    }

    
    @Transactional
    private void run(PopulationScrapYearMonth yearMonth) {
        List<Population> populations = populationScraper.scrap(yearMonth);
        populationDataService.save(yearMonth, populations);
    }


    private LocalDate getLastMonth() {
        return LocalDate.now().minusMonths(1);
    }

}
