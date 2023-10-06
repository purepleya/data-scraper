package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        PopulationScrapParameter lv1 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        PopulationScrapParameter lv2 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                2,
                1,
                1
        );

        PopulationScrapParameter lv3 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                3,
                1,
                1
        );

        PopulationScrapParameter lv4 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                4,
                1,
                1
        );

        assertTrue(lv1.hasNextLv());
        assertTrue(lv2.hasNextLv());
        assertTrue(lv3.hasNextLv());
        assertFalse(lv4.hasNextLv());
    }


    @Test
    @DisplayName("nextLevelParameters() 메서드에 올바르지 않은 파라미터가 전달되는 경우 빈 배열이 반환된다.")
    void getNextLvParameters1() {
        PopulationScrapParameter lv1 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        PopulationScrapParameter lv5 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                5,
                1,
                1
        );
        List<Population> results = List.of(
                new Population(new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "111", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))
                );

        List<PopulationScrapParameter> nextLvParameters1 = lv1.getNextLvParameters(null);
        List<PopulationScrapParameter> nextLvParameters2 = lv5.getNextLvParameters(results);

        assertNotNull(nextLvParameters1);
        assertNotNull(nextLvParameters2);
        assertEquals(0, nextLvParameters1.size());
        assertEquals(0, nextLvParameters2.size());
    }


    @Test
    @DisplayName("다음 레벨 파라미터를 생성해서 반환한다.")
    void getNextLvParameters2() {
        PopulationScrapParameter lv1 = new PopulationScrapParameter(
                PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );

        List<Population> results = List.of(
                new Population(new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "111", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
                new Population(new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "222", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
                new Population(new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "333", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
                new Population(new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "444", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))
        );

        List<PopulationScrapParameter> nextLvParameters = lv1.getNextLvParameters(results);

        assertEquals(4, nextLvParameters.size());
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("111") && p.getLv() == 2));
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("222") && p.getLv() == 2));
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("333") && p.getLv() == 2));
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("444") && p.getLv() == 2));
    }

}