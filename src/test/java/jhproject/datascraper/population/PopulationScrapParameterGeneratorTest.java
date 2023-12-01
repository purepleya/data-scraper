package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationRepository;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PopulationScrapParameterGeneratorTest {

    private final PopulationRepository populationRepository = mock(PopulationRepository.class);
    private final PopulationScrapLogRepository populationScrapLogRepository = mock(PopulationScrapLogRepository.class);

    @Test
    @DisplayName("데이터가 한건도 없으면 초기 파라미터를 생성한다.")
    void whenNoData_returnInitialParameter() {
        when(populationScrapLogRepository.getLastLog()).thenReturn(Optional.empty());
        
        PopulationScrapParameterGenerator generator = new PopulationScrapParameterGenerator(populationRepository, populationScrapLogRepository);
        Optional<PopulationScrapParameter> parameterOption = generator.generate();

        assertTrue(parameterOption.isPresent());
        PopulationScrapParameter parameter = parameterOption.get();
        assertEquals(parameter.getYearMonth(), "202210");
        assertEquals(parameter.getStdgCd(), "1000000000");
        assertEquals(parameter.getLv(), 1);
        assertEquals(parameter.getRegSeCd(), 1);
    }

    @Test
    @DisplayName("모든데이터가 다 수집 되었다면 다음 달 초기 파라미터를 반환한다.")
    void whenAllDataCollected_returnNextMonthInitialParameter() {
        when(populationScrapLogRepository.getLastLog())
                .thenReturn(Optional.of(
                        new PopulationScrapLog(
                                "202210",
                                "1000000011",
                                4,
                                4,
                                LocalDateTime.now(),
                                LocalDateTime.now())
                ));
        PopulationScrapParameterGenerator generator = new PopulationScrapParameterGenerator(populationRepository, populationScrapLogRepository);
        Optional<PopulationScrapParameter> parameterOption = generator.generate();

        assertTrue(parameterOption.isPresent());
        PopulationScrapParameter parameter = parameterOption.get();
        assertEquals(parameter.getYearMonth(), "202211");
        assertEquals(parameter.getStdgCd(), "1000000000");
        assertEquals(parameter.getLv(), 1);
        assertEquals(parameter.getRegSeCd(), 1);
    }



    @Test
    @DisplayName("next() 테스트 - 동일한 RegSeCd에서 현재 레벨의 데이터가 처리되다 말았다면 동일조건의 다음 stdgCd를 가지는 파라미터를 반환한다.")
    void whenNotAllDataCollectedAndCollectedSomeInSameLv_returnNextStdgCdParameter() {
        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
        when(populationRepository.findByYearMonthAndRegSeCdAndGoeLv(anyString(), any(), any()))
                .thenReturn(List.of(
                        new Population(lv1Parameter.getYearMonth(), (lv1Parameter.getRegSeCd()), "ctpvNm", "dongNm", "tong", "ban", "liNm", (lv1Parameter.getLv() + 1), "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm"),
                        new Population(lv1Parameter.getYearMonth(), (lv1Parameter.getRegSeCd()), "ctpvNm", "dongNm", "tong", "ban", "liNm", (lv1Parameter.getLv() + 1), "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm"),
                        new Population(lv1Parameter.getYearMonth(), (lv1Parameter.getRegSeCd()), "ctpvNm", "dongNm", "tong", "ban", "liNm", (lv1Parameter.getLv() + 1), "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm")
                ));

        when(populationScrapLogRepository.getLastLog())
                .thenReturn(Optional.of(new PopulationScrapLog(lv1Parameter.getYearMonth(), "111", 2, 1, LocalDateTime.now(), LocalDateTime.now())));

        PopulationScrapParameterGenerator generator = new PopulationScrapParameterGenerator(populationRepository, populationScrapLogRepository);


        Optional<PopulationScrapParameter> nextParameterOptional = generator.generate();
        assertTrue(nextParameterOptional.isPresent());
        assertEquals(lv1Parameter.getYearMonth(), nextParameterOptional.get().getYearMonth());
        assertEquals(2, nextParameterOptional.get().getLv());
        assertEquals("222", nextParameterOptional.get().getStdgCd());
        assertEquals(1, nextParameterOptional.get().getRegSeCd());
    }

//    @Test
//    @DisplayName("next() 테스트 - 동일한 RegSeCd에서 현재 레벨의 데이터가 전혀 처리되지 않았다면 동일조건의 처음(가장 작은 stdgCd를 가지는 파라미터를 반환한다. - 1")
//    void whenNoDataCollectedInSameLv_returnNextStdgCdParameter1() {
//        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
//        PublicDataPopulationGetResponse.Item response1 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response2 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response3 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        List<Population> results = List.of(
//                new PopulationScrapData(lv1Parameter, response1).toEntity(),
//                new PopulationScrapData(lv1Parameter, response2).toEntity(),
//                new PopulationScrapData(lv1Parameter, response3).toEntity()
//        );
//
//        PopulationScrapParameter lastParameter = lv1Parameter;
//        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(results);
//        assertTrue(nextParameterOptional.isPresent());
//        assertEquals(lastParameter.getYearMonth(), nextParameterOptional.get().getYearMonth());
//        assertEquals(2, nextParameterOptional.get().getLv());
//        assertEquals("111", nextParameterOptional.get().getStdgCd());
//        assertEquals(1, nextParameterOptional.get().getRegSeCd());
//    }
//
//    @Test
//    @DisplayName("next() 테스트 - 동일한 RegSeCd에서 현재 레벨의 데이터가 전혀 처리되지 않았다면 동일조건의 처음(가장 작은 stdgCd를 가지는 파라미터를 반환한다. - 2")
//    void whenNoDataCollectedInSameLv_returnNextStdgCdParameter2() {
//        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
//        PopulationScrapParameter lv3Parameter = new PopulationScrapParameter(lv1Parameter.getYearMonth(), lv1Parameter.getStdgCd(), 3, 4, 1);
//        PublicDataPopulationGetResponse.Item response1 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response2 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response3 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        PopulationScrapParameter lv4Parameter1 = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "111", 4, 4, 1);
//        PublicDataPopulationGetResponse.Item response4 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "444", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PopulationScrapParameter lv4Parameter2 = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "222", 4, 4, 1);
//        PublicDataPopulationGetResponse.Item response5 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "555", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//
//
//        List<Population> results = List.of(
//                new PopulationScrapData(lv3Parameter, response1).toEntity(),
//                new PopulationScrapData(lv3Parameter, response2).toEntity(),
//                new PopulationScrapData(lv3Parameter, response3).toEntity(),
//                new PopulationScrapData(lv4Parameter1, response4).toEntity(),
//                new PopulationScrapData(lv4Parameter2, response5).toEntity()
//        );
//
//        PopulationScrapParameter lastParameter = lv4Parameter2;
//        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(results);
//        assertTrue(nextParameterOptional.isPresent());
//        assertEquals(lastParameter.getYearMonth(), nextParameterOptional.get().getYearMonth());
//        assertEquals(4, nextParameterOptional.get().getLv());
//        assertEquals("333", nextParameterOptional.get().getStdgCd());
//        assertEquals(4, nextParameterOptional.get().getRegSeCd());
//    }
//
//
//
//    @Test
//    @DisplayName("next() 테스트 - 현재 레벨의 데이터가 전부 처리되었다면 다음 레벨의 파라미터를 반환한다.")
//    void whenAllDataCollectedInSameLv_returnNextLvParameter() {
//        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
//        PublicDataPopulationGetResponse.Item response1 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response2 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response3 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        PopulationScrapParameter lv2Parameter1 = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "111", 2, 1, 1);
//        PublicDataPopulationGetResponse.Item response4 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "444", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        PopulationScrapParameter lv2Parameter2 = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "222", 2, 1, 1);
//        PublicDataPopulationGetResponse.Item response5 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "555", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        PopulationScrapParameter lv2Parameter3 = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "333", 2, 1, 1);
//        PublicDataPopulationGetResponse.Item response6 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "666", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 16, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//
//        List<Population> results = List.of(
//                new PopulationScrapData(lv1Parameter, response1).toEntity(),
//                new PopulationScrapData(lv1Parameter, response2).toEntity(),
//                new PopulationScrapData(lv1Parameter, response3).toEntity(),
//                new PopulationScrapData(lv2Parameter1, response4).toEntity(),
//                new PopulationScrapData(lv2Parameter2, response5).toEntity(),
//                new PopulationScrapData(lv2Parameter3, response6).toEntity()
//        );
//
//        PopulationScrapParameter lastParameter = lv2Parameter3;
//        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(results);
//        assertTrue(nextParameterOptional.isPresent());
//        assertEquals(lastParameter.getYearMonth(), nextParameterOptional.get().getYearMonth());
//        assertEquals(3, nextParameterOptional.get().getLv());
//        assertEquals("444", nextParameterOptional.get().getStdgCd());
//        assertEquals(1, nextParameterOptional.get().getRegSeCd());
//    }
//
//    @Test
//    @DisplayName("next() 테스트 - 모든 레벨의 데이터가 전부 처리되었다면 다음 regSeCd의 파라미터를 반환한다.")
//    void whenAllDataCollectedInAllLv_returnNextRegSeCdParameter() {
//        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
//        PopulationScrapParameter lv3Parameter = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "111", 3, 1, 1);
//        PublicDataPopulationGetResponse.Item response1 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response2 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response3 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        List<Population> results = List.of(
//                new PopulationScrapData(lv3Parameter, response1).toEntity(),
//                new PopulationScrapData(lv3Parameter, response2).toEntity(),
//                new PopulationScrapData(lv3Parameter, response3).toEntity()
//        );
//
//        PopulationScrapParameter lv4Parameter = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "333", 4, 1, 1);
//
//        PopulationScrapParameter lastParameter = lv4Parameter;
//        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(results);
//        assertTrue(nextParameterOptional.isPresent());
//        assertEquals(lastParameter.getYearMonth(), nextParameterOptional.get().getYearMonth());
//        assertEquals(1, nextParameterOptional.get().getLv());
//        assertEquals(lv1Parameter.getStdgCd(), nextParameterOptional.get().getStdgCd());
//        assertEquals(2, nextParameterOptional.get().getRegSeCd());
//
//    }
//
//
//    @Test
//    @DisplayName("next() 테스트 - 마지막 레벨, 마지막 RegSeCd 인 경우 다음 달의 첫번째 레벨, 첫번째 RegSeCd 를 반환한다.")
//    void whenLastLvAndLastRegSeCd_returnNextMonthFirstLvFirstRegSeCd() {
//        PopulationScrapParameter lv1Parameter = PopulationScrapParameter.first();
//        PopulationScrapParameter lv3Parameter = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "111", 3, 4, 1);
//        PublicDataPopulationGetResponse.Item response1 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "111", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response2 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "222", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//        PublicDataPopulationGetResponse.Item response3 = new PublicDataPopulationGetResponse.Item("ctpvNm", "dongNm", "tong", "ban", "liNm", "333", "stdgNm", "sggNm", "admmCd", 1, 2, 3, 4, 2, 6, 7, 8, 9, 10, 11 ,12, 13, 14, 15, 2, 17, 18, 19, 20, 21 ,22, 23, 24, 25, "statsYm");
//
//        List<Population> results = List.of(
//                new PopulationScrapData(lv3Parameter, response1).toEntity(),
//                new PopulationScrapData(lv3Parameter, response2).toEntity(),
//                new PopulationScrapData(lv3Parameter, response3).toEntity()
//        );
//
//        PopulationScrapParameter lv4Parameter = new PopulationScrapParameter(lv1Parameter.getYearMonth(), "333", 4, 4, 1);
//
//        PopulationScrapParameter lastParameter = lv4Parameter;
//        Optional<PopulationScrapParameter> nextParameterOptional = lastParameter.next(results);
//        PopulationScrapParameter firstParameterOfNextMonth = PopulationScrapParameter.firstOf(PopulationScrapYearMonth.FIRST_YEAR_MONTH.nextMonth());
//
//        assertTrue(nextParameterOptional.isPresent());
//        assertEquals(firstParameterOfNextMonth.getYearMonth(), nextParameterOptional.get().getYearMonth());
//        assertEquals(firstParameterOfNextMonth.getLv(), nextParameterOptional.get().getLv());
//        assertEquals(firstParameterOfNextMonth.getStdgCd(), nextParameterOptional.get().getStdgCd());
//        assertEquals(firstParameterOfNextMonth.getRegSeCd(), nextParameterOptional.get().getRegSeCd());
//    }



}