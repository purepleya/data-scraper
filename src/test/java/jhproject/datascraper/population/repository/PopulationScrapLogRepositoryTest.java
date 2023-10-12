package jhproject.datascraper.population.repository;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PopulationScrapLogRepositoryTest {

    @Autowired
    private PopulationScrapLogRepository populationScrapLogRepository;

    @Test
    @DisplayName("로그 데이터가 없으면 빈 값을 반환한다.")
    void getLastLog_shouldReturn_empty_whenNoLog() {
//        해당 테이블 (population_scrap_log) 에 빈 값이 들어 가도록 세팅
        Optional<PopulationScrapLog> result = populationScrapLogRepository.getLastLog();

        assertTrue(result.isEmpty());
    }


    @Test
    @DisplayName("로그 데이터가 있으면 마지막 로그를 반환한다.")
    void getLastLog_shouldReturn_lastLog_whenExistLog() {
//        해당 테이블에 마지막 데이터가 202210 이 되도록 세팅

        Optional<PopulationScrapLog> result = populationScrapLogRepository.getLastLog();

        assertTrue(result.isPresent());
        assertEquals(2022, result.get().getYyyy());
        assertEquals(10, result.get().getMm());
    }

}