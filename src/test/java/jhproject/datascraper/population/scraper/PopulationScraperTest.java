package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopulationScraperTest {

    private PopulationScraper buildScraper() {
        return new PopulationScraper();
    }

    @Test
    @DisplayName("null 파라미터를 받으면 빈 리스트를 반환한다.")
    void whenScrapWithNullParameter_shouldReturnEmptyList() {
        PopulationScraper scraper = buildScraper();

        List<Population> result = scraper.scrap(null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }


    @Test
    @DisplayName("유효하지 않은 년월 파라미터를 받으면 빈 리스트를 반환한다.")
    void whenScrap_shouldReturnEmptyList() {
        PopulationScraper scraper = buildScraper();

        List<Population> result = scraper.scrap(PopulationScrapYearMonth.of(LocalDate.of(2022, 10, 1).minusMonths(2)));
        List<Population> result2 = scraper.scrap(PopulationScrapYearMonth.of(LocalDate.now()));

        assertNotNull(result);
        assertEquals(0, result.size());

        assertNotNull(result2);
        assertEquals(0, result2.size());
    }


    @Test
    @DisplayName("scrap 함수는 모든 regSeCd에 대해서 scrap을 수행 해야 한다. (모든 lv, page에 대해 scrap을 수행하는 기능은 다른 Scraper 의 테스트를 통해 검증한다.)")
    void whenScrap_shouldScrapAllRegSeCd() {

    }

}