package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PopulationLvScraperTest {


    @Test
    @DisplayName("페이지가 여러개 일 경우, 모든 페이지를 scrap 한다.")
    void whenScrap_shouldScrapAllPages() {
//        given
        PublicDataPopulationClient publicDataPopulationClient = (PublicDataPopulationGetParameter parameter) -> {
            PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.pageNo(), "0", 2, 1, "success");
            PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of());
            if (parameter.lv().equals("1")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000001111", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            }

            return new PublicDataPopulationGetResponse(head, items);
        };

        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

//        when
        PopulationLvScraper scraper = new PopulationLvScraper(new PopulationPageScraper(publicDataPopulationClient));
        List<Population> result = scraper.scrap(parameter);

//        then
        assertEquals(2, result.size());
    }



    @Test
    @DisplayName("최초 scrap 결과를 바탕으로 하위 lv 파라미터를 생성하여 재귀적으로 scrap 을 실행한다.")
    void whenScrap_shouldScrapByLv() {
//        given
        PublicDataPopulationClient publicDataPopulationClient = (PublicDataPopulationGetParameter parameter) -> {
            PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.pageNo(), "0", 1, 1, "success");
            PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of());
            if (parameter.lv().equals("1")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1000000001", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
            } else if (parameter.lv().equals("2")) {
                items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "2", "1000000011", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,"202101")));
            } else if (parameter.lv().equals("3")) {
                head = new PublicDataPopulationGetResponse.Head(parameter.pageNo(), "0", 2, 1, "success");
                if (parameter.pageNo().equals(1)) {
                    items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "2", "1000000111", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "202101")));
                } else if (parameter.pageNo().equals(2)) {
                    items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "2", "1000000211", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "202101")));
                }
            } else if (parameter.lv().equals("4")) {
                if (parameter.stdgCd().equals("1000000111")) {
                    items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "2", "1000001111", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "202101")));
                } else if (parameter.stdgCd().equals("1000000211")) {
                    items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "2", "1000002111", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "202101")));
                }
            }

            return new PublicDataPopulationGetResponse(head, items);
        };

        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        PopulationLvScraper scraper = new PopulationLvScraper(new PopulationPageScraper(publicDataPopulationClient));
        List<Population> result = scraper.scrap(parameter);

        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000001")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000011")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000111")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000211")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000001111")));
        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000002111")));

        assertEquals(6, result.size());
    }


}
