package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import jhproject.datascraper.population.entity.Population;
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
        assertEquals("serviceKey", result.getServiceKey());
        assertEquals("1000000000", result.getStdgCd());
        assertEquals("202210", result.getSrchFrYm());
        assertEquals("202210", result.getSrchToYm());
        assertEquals("1", result.getLv());
        assertEquals("1", result.getRegSeCd());
        assertEquals("JSON", result.getType());
        assertEquals("1000", result.getNumOfRows());
        assertEquals(1, result.getPageNo());
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

        Optional<PopulationScrapParameter> regSeCd2Optional = regSeCd1.getNextRegSeCdParameter();
        PopulationScrapParameter regSeCd2 = regSeCd2Optional.get();

        Optional<PopulationScrapParameter> regSeCd3Optional = regSeCd2.getNextRegSeCdParameter();
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
        Optional<PopulationScrapParameter> regSeCd5Optional = regSeCd4.getNextRegSeCdParameter();

        assertTrue(regSeCd5Optional.isEmpty());
    }


    @Test
    @DisplayName("next() 테스트 - 마지막 레벨, 마지막 RegSeCd 인 경우 다음 달의 첫번째 레벨, 첫번째 RegSeCd 를 반환한다.")
    void whenLastLvAndLastRegSeCd_returnNextMonthFirstLvFirstRegSeCd() {
        PopulationScrapParameter lastLvLastRegSeCd = new PopulationScrapParameter(PopulationScrapYearMonth.FIRST_YEAR_MONTH.getYearMonth(), "1000000000", 4, 4, 1);
        Optional<PopulationScrapParameter> nextParameterOptional = lastLvLastRegSeCd.next(List.of());

        assertTrue(nextParameterOptional.isPresent());
        PopulationScrapParameter nextParameter = nextParameterOptional.get();
        assertEquals("202211", nextParameter.getYearMonth());
        assertEquals("1000000000", nextParameter.getStdgCd());
        assertEquals(1, nextParameter.getLv());
        assertEquals(1, nextParameter.getRegSeCd());
        assertEquals(1, nextParameter.getPageNo());
    }

    @Test
    @DisplayName("next() 테스트 - 동일한 RegSeCd에서 현재 레벨의 데이터가 처리되다 말았다면 동일조건의 다음 stdgCd를 가지는 파라미터를 반환한다.")
    void whenNotAllDataCollectedAndCollectedSomeInSameLv_returnNextStdgCdParameter() {
        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
        PublicDataPopulationGetResponse.Item response1 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
        PublicDataPopulationGetResponse.Item response2 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
        PublicDataPopulationGetResponse.Item response3 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");

        List<Population> results = List.of(
                new PopulationScrapData(lv1Parameter, response1).toEntity(),
                new PopulationScrapData(lv1Parameter, response2).toEntity(),
                new PopulationScrapData(lv1Parameter, response3).toEntity()
        );

        PopulationScrapParameter lastParameter = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "111", 2, 1, 1);
        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(results);
        assertTrue(nextParameterOptional.isPresent());
        assertEquals(lastParameter.getYearMonth(), nextParameterOptional.get().getYearMonth());
        assertEquals(2, nextParameterOptional.get().getLv());
        assertEquals("222", nextParameterOptional.get().getStdgCd());
        assertEquals(1, nextParameterOptional.get().getRegSeCd());
    }

    @Test
    @DisplayName("next() 테스트 - 동일한 RegSeCd에서 현재 레벨의 데이터가 전혀 처리되지 않았다면 동일조건의 처음(가장 작은 stdgCd를 가지는 파라미터를 반환한다.")
    void whenNoDataCollectedInSameLv_returnNextStdgCdParameter() {
//        TODO
    }


}
