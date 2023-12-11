package jhproject.datascraper.population;

import jhproject.datascraper.TestContainerConfiguration;
import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationRepository;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScraper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(TestContainerConfiguration.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto = none")
@Sql({"/sql/population/repository/CREATE_population.sql", "/sql/population/repository/CREATE_population_scrap_log.sql"})
class PopulationDataProcessorTest {


    @Autowired
    PopulationScrapLoggerBuilder loggerBuilder;

    @Autowired
    PopulationScraper populationScraper;

    @Autowired
    PopulationDataRegister populationDataRegister;

    @Autowired
    PopulationScrapParameterGenerator defaultPopulationScrapParameterGenerator;

    @Autowired
    PopulationScrapLogRepository populationScrapLogRepository;
    @Autowired
    PopulationRepository populationRepository;

    @Test
    @DisplayName("통합테스트")
    void run() throws Exception {
        PopulationScrapParameterGenerator populationScrapParameterGenerator = Mockito.mock(PopulationScrapParameterGenerator.class);

        when(populationScrapParameterGenerator.generate()).thenAnswer(invocation -> {
            var parameter = defaultPopulationScrapParameterGenerator.generate();
            if (parameter.isPresent() && parameter.get().getLv() > PopulationScrapParameter.lvs[2]) {
                return Optional.empty();
            } else {
                return parameter;
            }
        });

        PopulationDataProcessor populationDataProcessor = new PopulationDataProcessor(populationScrapParameterGenerator, loggerBuilder, populationScraper, populationDataRegister);
        populationDataProcessor.run();

        var scrapLogs = populationScrapLogRepository.findAll();

        assertThat(scrapLogs).isNotEmpty();
        assertThat(
                scrapLogs.stream().map(PopulationScrapLog::getLv).filter(lv -> lv >= PopulationScrapParameter.lvs[PopulationScrapParameter.lvs.length - 1]).count()
        ).isGreaterThan(0);
        assertThat(
                scrapLogs.stream().map(PopulationScrapLog::getLv).filter(lv -> lv == PopulationScrapParameter.lvs[1]).count()
        ).isGreaterThan(17);

        assertThat(
                scrapLogs.stream().map(PopulationScrapLog::getLv).filter(lv -> lv == PopulationScrapParameter.lvs[2]).count()
        ).isGreaterThan(260);
    }


}