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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Import(TestContainerConfiguration.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto = none")
class PopulationScrapLogRepositoryTest {

    @Autowired
    private PopulationScrapLogRepository populationScrapLogRepository;

    @Test
    @Sql("/sql/population/repository/CREATE_population_scrap_log.sql")
    @DisplayName("종료일자(scrapEndDtm) 기준으로 마지막 로그를 가져온다.")
    void getLastLog() {

        populationScrapLogRepository.saveAllAndFlush(
                List.of(
                        new PopulationScrapLog("202210", "100000", 1, 1, LocalDateTime.of(2023, 11, 22, 20, 0, 0), LocalDateTime.of(2023, 11, 22, 20, 5, 0)),
                        new PopulationScrapLog("202210", "100001", 2, 1, LocalDateTime.of(2023, 11, 22, 20, 10, 0), LocalDateTime.of(2023, 11, 22, 20, 15, 0)),
                        new PopulationScrapLog("202210", "100002", 2, 1, LocalDateTime.of(2023, 11, 22, 20, 20, 0), LocalDateTime.of(2023, 11, 22, 20, 25, 0)),
                        new PopulationScrapLog("202210", "100003", 2, 1, LocalDateTime.of(2023, 11, 22, 20, 30, 0), LocalDateTime.of(2023, 11, 22, 20, 35, 0))
                )
        );


        Optional<PopulationScrapLog> lastLogOptional = populationScrapLogRepository.getLastLog();

        assertTrue(lastLogOptional.isPresent());
        PopulationScrapLog lastLog = lastLogOptional.get();
        assertEquals(lastLog.getStdgCd(), "100003");
        assertEquals(lastLog.getScrapEndDtm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "2023-11-22 20:35:00");
    }



}