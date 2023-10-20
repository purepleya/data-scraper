package jhproject.datascraper.population.repository;

import jhproject.datascraper.TestContainerConfiguration;
import jhproject.datascraper.population.entity.PopulationScrapLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Import(TestContainerConfiguration.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto = none")
class PopulationScrapLogRepositoryTest {

    @Autowired
    private PopulationScrapLogRepository populationScrapLogRepository;


    @Test
    @DisplayName("로그 데이터가 없으면 빈 값을 반환한다.")
    @Sql("/sql/population/repository/CREATE_population_scrap_log.sql")
    void getLastLog_shouldReturn_empty_whenNoLog() {
        Optional<PopulationScrapLog> result = populationScrapLogRepository.getLastLog();

        assertTrue(result.isEmpty());
    }


    @Test
    @DisplayName("로그 데이터가 있으면 마지막 로그를 반환한다.")
    @Sql({"/sql/population/repository/CREATE_population_scrap_log.sql", "/sql/population/repository/getLastLog_shouldReturn_lastLog_whenExistLog.sql"})
    void getLastLog_shouldReturn_lastLog_whenExistLog() {
        Optional<PopulationScrapLog> result = populationScrapLogRepository.getLastLog();

        assertTrue(result.isPresent());
        assertEquals(2022, result.get().getYyyy());
        assertEquals(10, result.get().getMm());
    }

}