package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationPageScraperTest {

    private final PublicDataPopulationClient publicDataPopulationClientForTest = new PublicDataPopulationClient() {
        @Override
        public PublicDataPopulationGetResponse getPopulation(PublicDataPopulationGetParameter parameter) {
            if (parameter.getLv().equals("1")) {
//                결과가 없는 경우
                return new PublicDataPopulationGetResponse();
            } else if (parameter.getLv().equals("2")) {
//                결과가 여러 2개 페이지로 나눠진 경우
                PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.getPageNo(), "0", 2, 1, "success");
                PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
                return new PublicDataPopulationGetResponse(head, items);
            } else if (parameter.getLv().equals("3")) {
//                결과가 여러 1개 페이지인 경우
                PublicDataPopulationGetResponse.Head head = new PublicDataPopulationGetResponse.Head(parameter.getPageNo(), "0", 1, 1, "success");
                PublicDataPopulationGetResponse.Items items = new PublicDataPopulationGetResponse.Items(List.of(new PublicDataPopulationGetResponse.Item("서울특별시", "종로구", "1", "1", "1", "1", "1", "1", "1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "202101")));
                return new PublicDataPopulationGetResponse(head, items);
            } else {
                return null;

            }
        }
    };


    private final PopulationPageScraper pupulationPageScraper = new PopulationPageScraper(publicDataPopulationClientForTest);



    @Test
    @DisplayName("결과가 존재하지 않는경우 빈 리스트를 반환한다.")
    void whenNoResult_shouldReturnEmptyList() {
        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        List<PopulationScrapData> result = pupulationPageScraper.scrap(parameter);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("결과가 여러 페이지로 나눠진 경우 모든 페이지를 수집한다.")
    void whenResultHasMultiplePages_shouldReturnAllPageDatas() {
        // given
        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                2,
                1,
                1
        );

        // when
        List<PopulationScrapData> result = pupulationPageScraper.scrap(parameter);

        // then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("결과가 한 페이지로 나눠진 경우 한 페이지만 수집한다.")
    void whenResultHasSinglePage_shouldReturnOnePageData() {
        // given
        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                3,
                1,
                1
        );

        // when
        List<PopulationScrapData> result = pupulationPageScraper.scrap(parameter);

        // then
        assertEquals(1, result.size());
    }




}
