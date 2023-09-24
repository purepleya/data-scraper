package jhproject.datascraper.population;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final LocalDate FIRST_MONTH = LocalDate.of(2022, 10, 1);

    private final PopulationDataScraper populationDataScraper;
    private final PopulationDataRegister populationDataService;

    public void run() {
//        최초일(2022.10) 부터 지난달 까지 수집
        LocalDate lastMonth = getLastMonth();
        var currentMonth = FIRST_MONTH;

        while (currentMonth.isBefore(lastMonth)) {
            run(currentMonth);
            currentMonth = currentMonth.plusMonths(1);
        }
    }

    
    @Transactional
    private void run(LocalDate currentMonth) {
        List<Population> populations = populationDataScraper.scrap(currentMonth);
        populationDataService.save(currentMonth, populations);
    }


    private LocalDate getLastMonth() {
        return LocalDate.now().minusMonths(1);
    }

}
