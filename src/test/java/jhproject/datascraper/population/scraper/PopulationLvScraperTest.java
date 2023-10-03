package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PopulationLvScraperTest {

    private final PopulationLvScraper scraper = new PopulationLvScraper();


    @Test
    @DisplayName("최초 scrap 결과를 바탕으로 하위 lv 파라미터를 생성하여 재귀적으로 scrap 을 실행한다.")
    void whenScrap_shouldScrapByLv() {
        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        List<Population> result = scraper.scrap(parameter);

        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000000")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000001")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000011")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000111")));
    }


}
