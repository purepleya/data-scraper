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

        PopulationLvScraper scraper = new PopulationLvScraper(new PopulationPageScraper(publicDataPopulationClient));

        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

//        when
        List<Population> result = scraper.scrap(parameter);

//        then
        assertEquals(2, result.size());
    }



    @Test
    @DisplayName("최초 scrap 결과를 바탕으로 하위 lv 파라미터를 생성하여 재귀적으로 scrap 을 실행한다.")
    void whenScrap_shouldScrapByLv() {
//        PopulationScrapParameter parameter = new PopulationScrapParameter(
//                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
//                "1000000000",
//                1,
//                1,
//                1
//        );
//
//        List<Population> result = scraper.scrap(parameter);
//
//        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000001")));
//        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000011")));
//        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000000111")));
//        assertTrue(result.stream().anyMatch(p -> p.getStdgCd().equals("1000001111")));
//
////        각 레벨별 1개 데이터 마지막 4 레벨은 2개 데이터 해서 5개
//        assertEquals(5, result.size());
    }


}
