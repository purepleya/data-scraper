package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PopulationScrapParameterTest {

    @Test
    @DisplayName("기본 파라미터(stdgCd 가 1000000000 이고, lv, regSeCd, pageNo가 1인 객체)를 생성 한다.")
    void first_should() {
        PopulationScrapParameter first = PopulationScrapParameter.firstOf(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
        assertEquals("1000000000", first.getStdgCd());
        assertEquals(1, first.getLv());
        assertEquals(1, first.getRegSeCd());
        assertEquals(1, first.getPageNo());
    }

    @Test
    @DisplayName("페이지가 1 증가한 파라미터를 반환 한다.")
    void nextPage() {
        PopulationScrapParameter first = PopulationScrapParameter.firstOf(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
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
        List<PopulationScrapData> results = List.of(
                new PopulationScrapData(lv1, new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "111", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))
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

        List<PopulationScrapData> results = List.of(
                new PopulationScrapData(lv1, new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "111", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
                new PopulationScrapData(lv1, new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "222", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
                new PopulationScrapData(lv1, new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "333", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
                new PopulationScrapData(lv1, new PublicDataPopulationGetResponse.Item(null, null, null, null, null, "444", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))
        );

        List<PopulationScrapParameter> nextLvParameters = lv1.getNextLvParameters(results);

        assertEquals(4, nextLvParameters.size());
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("111") && p.getLv() == 2));
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("222") && p.getLv() == 2));
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("333") && p.getLv() == 2));
        assertTrue(nextLvParameters.stream().anyMatch(p -> p.getStdgCd().equals("444") && p.getLv() == 2));
    }


//    if (parameter.hasNextRegSeCd()) {
//        result.addAll(scrap(parameter.nextRegSeCd()));

    @Test
    @DisplayName("다음 regSeCd 파라미터가 존재하는지 확인한다.")
    void hasNextRegSeCd() {
        PopulationScrapParameter regSeCd1 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 1, 1);
        PopulationScrapParameter regSeCd2 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 2, 1);
        PopulationScrapParameter regSeCd3 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 3, 1);
        PopulationScrapParameter regSeCd4 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 4, 1);
        PopulationScrapParameter regSeCd5 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 5, 1);
        PopulationScrapParameter regSeCd6 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 6, 1);

        assertTrue(regSeCd1.hasNextRegSeCd());
        assertTrue(regSeCd2.hasNextRegSeCd());
        assertTrue(regSeCd3.hasNextRegSeCd());
        assertFalse(regSeCd4.hasNextRegSeCd());
        assertFalse(regSeCd5.hasNextRegSeCd());
        assertFalse(regSeCd6.hasNextRegSeCd());
    }


    @Test
    @DisplayName("다음 regSeCd 파라미터 기만의 파라미터 객체를 반환한다.")
    void nextRegSeCd1() {
        PopulationScrapParameter regSeCd1 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 1, 1);

        Optional<PopulationScrapParameter> regSeCd2Optional = regSeCd1.getNextRegSeCd();
        PopulationScrapParameter regSeCd2 = regSeCd2Optional.get();

        Optional<PopulationScrapParameter> regSeCd3Optional = regSeCd2.getNextRegSeCd();
        PopulationScrapParameter regSeCd3 = regSeCd3Optional.get();


        assertTrue(regSeCd2Optional.isPresent());
        assertEquals(regSeCd1.getYearMonth(), regSeCd2.getYearMonth());
        assertEquals(regSeCd1.getStdgCd(), regSeCd2.getStdgCd());
        assertEquals(regSeCd1.getLv(), regSeCd2.getLv());
        assertEquals(regSeCd1.getPageNo(), regSeCd2.getPageNo());
        assertEquals(2, regSeCd2.getRegSeCd());

        assertTrue(regSeCd3Optional.isPresent());
        assertEquals(regSeCd2.getYearMonth(), regSeCd3.getYearMonth());
        assertEquals(regSeCd2.getStdgCd(), regSeCd3.getStdgCd());
        assertEquals(regSeCd2.getLv(), regSeCd3.getLv());
        assertEquals(regSeCd2.getPageNo(), regSeCd3.getPageNo());
        assertEquals(3, regSeCd3.getRegSeCd());


    }

    @Test
    @DisplayName("다음 regSeCd 파라미터 기만의 파라미터 객체를 반환할때, 다음 regSeCd 가 없으면 Optional.empty() 를 반환한다.")
    void nextRegSeCd2() {
        PopulationScrapParameter regSeCd4 = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 1, 4, 1);
        Optional<PopulationScrapParameter> regSeCd5Optional = regSeCd4.getNextRegSeCd();

        assertTrue(regSeCd5Optional.isEmpty());
    }

}