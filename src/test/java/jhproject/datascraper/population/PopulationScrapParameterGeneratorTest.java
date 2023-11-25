package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PopulationScrapParameterGeneratorTest {

    private final PopulationScrapLogRepository populationScrapLogRepository = mock(PopulationScrapLogRepository.class);

    @Test
    @DisplayName("데이터가 한건도 없으면 초기 파라미터를 생성한다.")
    void whenNoData_returnInitialParameter() {
        when(populationScrapLogRepository.getLastLog()).thenReturn(Optional.empty());
        
        PopulationScrapParameterGenerator generator = new PopulationScrapParameterGenerator(populationScrapLogRepository);
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
        PopulationScrapParameterGenerator generator = new PopulationScrapParameterGenerator(populationScrapLogRepository);
        Optional<PopulationScrapParameter> parameterOption = generator.generate();

        assertTrue(parameterOption.isPresent());
        PopulationScrapParameter parameter = parameterOption.get();
        assertEquals(parameter.getYearMonth(), "202211");
        assertEquals(parameter.getStdgCd(), "1000000000");
        assertEquals(parameter.getLv(), 1);
        assertEquals(parameter.getRegSeCd(), 1);
    }



//    @Test
//    @DisplayName("모든데이터가 다 수집 되고, 다음 달이 없다면 빈값을 반환한다.")



}