package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PopulationScrapNextYearMonthFinderTest {

    @Test
    @DisplayName("데이터 수집 이력이 없는 경우에는 최초 데이터 제공 월(2022.10) 이 반환 한다.")
    void find_shouldReturn_firstYearMonth_whenNoData() {
        PopulationScrapNextYearMonthFinder populationDataScraperNextYearMonthFinder = new PopulationScrapNextYearMonthFinder(null);
        Optional<PopulationScrapYearMonth> yearMonthOptional = populationDataScraperNextYearMonthFinder.find();

        assertEquals(PopulationScrapYearMonth.FIRST_YEAR_MONTH, yearMonthOptional.get());
    }

    @Test
    @DisplayName("가장 마지막 달(현재월 -1)까지 수집 된 경우는 빈 값을 반환한다.")
    void find_shouldReturn_empty_whenLastMonth() {
        PopulationScrapNextYearMonthFinder populationDataScraperNextYearMonthFinder = new PopulationScrapNextYearMonthFinder(null);
        Optional<PopulationScrapYearMonth> yearMonthOptional = populationDataScraperNextYearMonthFinder.find();

        assertTrue(yearMonthOptional.isEmpty());
    }

}