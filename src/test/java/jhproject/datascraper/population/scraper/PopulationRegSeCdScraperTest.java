package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PopulationRegSeCdScraperTest {

    @Test
    @DisplayName("null 파라미터를 받으면 빈 리스트를 반환한다.")
    void whenScrap_shouldReturnEmptyList() {
        PopulationRegSeCdScraper populationRegSeCdScraper = new PopulationRegSeCdScraper(null);

        List<PopulationScrapData> result = populationRegSeCdScraper.scrap(null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }




    @Test
    @DisplayName("scrap 함수는 모든 lv에 대해서 scrap을 수행 해야 한다. (모든 페이지에 대해 scrap을 수행하는 기능은 PopulationLvScraperTest 에서 검증한다.)")
    void whenScrap_shouldScrapAllLv() {
        PublicDataPopulationClient publicDataPopulationClient = (PublicDataPopulationGetParameter parameter) -> {
            PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.pageNo(), "0", 1, 1, "success");
            PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of());
            if (parameter.regSeCd().equals("1") && parameter.lv().equals("1")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000001", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.regSeCd().equals("1") && parameter.lv().equals("2")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000011", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.regSeCd().equals("1") && parameter.lv().equals("3")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000111", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            }
            return new PublicDataPopulationGetResponse(head, items);
        };

        PopulationRegSeCdScraper populationRegSeCdScraper = new PopulationRegSeCdScraper(new PopulationLvScraper(new PopulationPageScraper(publicDataPopulationClient)));

        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        List<PopulationScrapData> result = populationRegSeCdScraper.scrap(parameter);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("1000000001", result.get(0).getStdgCd());
        assertEquals("1000000011", result.get(1).getStdgCd());
        assertEquals("1000000111", result.get(2).getStdgCd());
    }


    @Test
    @DisplayName("최초 scrap 결과를 바탕으로 다음 regSeCd 값을 기반으로 하는 파라미터를 생성하여 재귀적으로 scrap 을 실행한다.")
    void whenScrap_shouldScrapByRegSeCd() {
        PublicDataPopulationClient publicDataPopulationClient = (PublicDataPopulationGetParameter parameter) -> {
            PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.pageNo(), "0", 1, 1, "success");
            PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of());
            if (parameter.regSeCd().equals("1") && parameter.lv().equals("1")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000001", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.regSeCd().equals("1") && parameter.lv().equals("2")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000011", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.regSeCd().equals("2") && parameter.lv().equals("1")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000009", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.regSeCd().equals("2") && parameter.lv().equals("2")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000019", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.regSeCd().equals("2") && parameter.lv().equals("3")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000119", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            }
            return new PublicDataPopulationGetResponse(head, items);
        };

        PopulationRegSeCdScraper populationRegSeCdScraper = new PopulationRegSeCdScraper(new PopulationLvScraper(new PopulationPageScraper(publicDataPopulationClient)));

        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        List<PopulationScrapData> result = populationRegSeCdScraper.scrap(parameter);

        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("1000000001", result.get(0).getStdgCd());
        assertEquals("1000000011", result.get(1).getStdgCd());
        assertEquals("1000000009", result.get(2).getStdgCd());
        assertEquals("1000000019", result.get(3).getStdgCd());
        assertEquals("1000000119", result.get(4).getStdgCd());

    }

}
