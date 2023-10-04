package jhproject.datascraper.population.scraper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationScrapParameterTest {

    @Test
    @DisplayName("기본 파라미터(stdgCd 가 1000000000 이고, lv, regSeCd, pageNo가 1인 객체)를 생성 한다.")
    void first_should() {
        PopulationScrapParameter first = PopulationScrapParameter.first(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
        assertEquals("1000000000", first.getStdgCd());
        assertEquals(1, first.getLv());
        assertEquals(1, first.getRegSeCd());
        assertEquals(1, first.getPageNo());
    }

    @Test
    @DisplayName("페이지가 1 증가한 파라미터를 반환 한다.")
    void nextPage() {
        PopulationScrapParameter first = PopulationScrapParameter.first(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
        assertEquals(1, first.getPageNo());

        PopulationScrapParameter nextPage = first.nextPage();
        assertEquals(2, nextPage.getPageNo());

        assertEquals(first.getYearMonth(), nextPage.getYearMonth());
        assertEquals(first.getStdgCd(), nextPage.getStdgCd());
        assertEquals(first.getLv(), nextPage.getLv());
        assertEquals(first.getRegSeCd(), nextPage.getRegSeCd());
    }

    @Test
    @DisplayName("PopulationScrapParameterTest 객체를 PublicDataPopulationGetParameter 객체로 변환한다.")
    void toPublicDataPopulationGetParameter() {
        // given
        PopulationScrapParameter parameter = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        // when
        PublicDataPopulationGetParameter result = parameter.toPublicDataPopulationGetParameter("serviceKey");

        // then
        assertEquals("serviceKey", result.serviceKey());
        assertEquals("1000000000", result.stdgCd());
        assertEquals("202210", result.srchFrYm());
        assertEquals("202210", result.srchToYm());
        assertEquals("1", result.lv());
        assertEquals("1", result.regSeCd());
        assertEquals("JSON", result.type());
        assertEquals("1000", result.numOfRows());
        assertEquals(1, result.pageNo());
    }

    @Test
    @DisplayName("다음 레벨이 존재하는지 여부를 반환한다.")
    void hasNextLv() {
        assertEquals(true, false);
    }

    @Test
    @DisplayName("다음 레벨 파라미터를 생성해서 반환한다.")
    void getNextLvParameters() {
        assertEquals(true, false);
    }
}