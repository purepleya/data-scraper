package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopulationScraperTest {

    private PopulationScraper buildScraper(PublicDataPopulationClient publicDataPopulationClient) {
        return new PopulationScraper(publicDataPopulationClient);
    }


    @Test
    @DisplayName("null 파라미터를 받으면 빈 리스트를 반환한다.")
    void whenScrapWithNullParameter_shouldReturnEmptyList() {
        PopulationScraper scraper = buildScraper(null);

        List<PopulationScrapData> result = scraper.scrap(null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }


    @Test
    @DisplayName("유효하지 않은 년월 파라미터를 받으면 빈 리스트를 반환한다.")
    void whenScrap_shouldReturnEmptyList() {
        PopulationScraper scraper = buildScraper(null);

        PopulationScrapParameter parameter = new PopulationScrapParameter("202206", "1000000000", 1, 1);
        List<PopulationScrapData> result = scraper.scrap(parameter);

        PopulationScrapParameter parameter2 = new PopulationScrapParameter(PopulationScrapYearMonth.of(LocalDate.now()).getYearMonth(), "1000000000", 1, 1);
        List<PopulationScrapData> result2 = scraper.scrap(parameter2);

        assertNotNull(result);
        assertEquals(0, result.size());

        assertNotNull(result2);
        assertEquals(0, result2.size());
    }



    @Test
    @DisplayName("모든페이지를 scrap 하고 결과를 반환한다.")
    void whenScrap_shouldScrapAllPages() {
        PublicDataPopulationClient publicDataPopulationClient = (PublicDataPopulationGetParameter parameter) -> {
            PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.getPageNo(), "0", 5, 1, "success");
            PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of());

            if (parameter.getPageNo().equals(1)) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000001", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, parameter.getSrchFrYm())));
            } else if (parameter.getPageNo().equals(2)) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000011", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, parameter.getSrchFrYm())));
            } else if (parameter.getPageNo().equals(3)) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000009", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, parameter.getSrchFrYm())));
            } else if (parameter.getPageNo().equals(4)) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000019", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, parameter.getSrchFrYm())));
            } else if (parameter.getPageNo().equals(5)) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000119", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, parameter.getSrchFrYm())));
            } else if (parameter.getPageNo() > 5) {
                fail();
            }
            return new PublicDataPopulationGetResponse(head, items);
        };

        PopulationScraper scraper = buildScraper(publicDataPopulationClient);

        List<PopulationScrapData> result = scraper.scrap(PopulationScrapParameter.first());

        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("1000000001", result.get(0).getStdgCd());
        assertEquals("1000000011", result.get(1).getStdgCd());
        assertEquals("1000000009", result.get(2).getStdgCd());
        assertEquals("1000000019", result.get(3).getStdgCd());
        assertEquals("1000000119", result.get(4).getStdgCd());
    }

}