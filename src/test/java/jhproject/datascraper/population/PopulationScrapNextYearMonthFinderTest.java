package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PopulationScrapNextYearMonthFinderTest {

    private final PopulationScrapLogRepository populationScrapLogRepository = mock(PopulationScrapLogRepository.class);


    @Test
    @DisplayName("데이터 수집 이력이 없는 경우에는 최초 데이터 제공 월(2022.10) 이 반환 한다.")
    void find_shouldReturn_firstYearMonth_whenNoData() {
        Mockito.when(populationScrapLogRepository.getLastLog())
                .thenReturn(Optional.empty());

        PopulationScrapNextYearMonthFinder populationDataScraperNextYearMonthFinder = new PopulationScrapNextYearMonthFinder(populationScrapLogRepository);
        Optional<PopulationScrapYearMonth> yearMonthOptional = populationDataScraperNextYearMonthFinder.find();

        assertEquals(PopulationScrapYearMonth.FIRST_YEAR_MONTH, yearMonthOptional.get());
    }


    @Test
    @DisplayName("데이터 수집 이력이 있는 경우에는 마지막 데이터 수집 월의 다음 월을 반환 한다.")
    void find_shouldReturn_nextYearMonth_whenExistData() {
        PopulationScrapLog lastLog = new PopulationScrapLog(1, 2023, 1, null, null);
        Mockito.when(populationScrapLogRepository.getLastLog())
                .thenReturn(Optional.of(lastLog));

        PopulationScrapNextYearMonthFinder populationDataScraperNextYearMonthFinder = new PopulationScrapNextYearMonthFinder(populationScrapLogRepository);
        Optional<PopulationScrapYearMonth> yearMonthOptional = populationDataScraperNextYearMonthFinder.find();

        assertTrue(yearMonthOptional.isPresent());
        assertEquals("202302", yearMonthOptional.get().getYearMonth());
    }


    @Test
    @DisplayName("가장 마지막 달(현재월 -1)까지 수집 된 경우는 빈 값을 반환한다.")
    void find_shouldReturn_empty_whenLastMonth() {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);

        PopulationScrapLog lastLog = new PopulationScrapLog(1, lastMonth.getYear(), lastMonth.getMonthValue(), null, null);
        Mockito.when(populationScrapLogRepository.getLastLog())
                .thenReturn(Optional.of(lastLog));

        PopulationScrapNextYearMonthFinder populationDataScraperNextYearMonthFinder = new PopulationScrapNextYearMonthFinder(populationScrapLogRepository);
        Optional<PopulationScrapYearMonth> yearMonthOptional = populationDataScraperNextYearMonthFinder.find();

        assertTrue(yearMonthOptional.isEmpty());
    }

}