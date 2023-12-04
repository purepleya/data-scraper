package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PopulationScrapLoggerBuilder {

    private final PopulationScrapLogRepository populationScrapLogRepository;

    public PopulationScrapLogger build(PopulationScrapParameter parameter) {
        return new PopulationScrapLogger(populationScrapLogRepository, parameter);
    }


    @Slf4j
    public static class PopulationScrapLogger {
        private final PopulationScrapLogRepository populationScrapLogRepository;
        private final PopulationScrapParameter parameter;
        private LocalDateTime startDtm;

        private PopulationScrapLogger(PopulationScrapLogRepository populationScrapLogRepository, PopulationScrapParameter parameter) {
            this.populationScrapLogRepository = populationScrapLogRepository;
            this.parameter = parameter;
        }

        @Transactional
        public void start() {
            this.startDtm = LocalDateTime.now();
            log.info("Population scrap start. year: {}, month: {}, stdgCd: {}, lv: {}, regSeCd: {}",
                    parameter.getYearMonth().substring(0, 4),
                    parameter.getYearMonth().substring(4),
                    parameter.getStdgCd(),
                    parameter.getLv(),
                    parameter.getRegSeCd());
        }

        @Transactional
        public void end() {
            populationScrapLogRepository.save(new PopulationScrapLog(
                    parameter.getYearMonth(),
                    parameter.getStdgCd(),
                    parameter.getLv(),
                    parameter.getRegSeCd(),
                    startDtm,
                    LocalDateTime.now()
            ));

            log.info("Population scrap end. year: {}, month: {}, stdgCd: {}, lv: {}, regSeCd: {}",
                    parameter.getYearMonth().substring(0, 4),
                    parameter.getYearMonth().substring(4),
                    parameter.getStdgCd(),
                    parameter.getLv(),
                    parameter.getRegSeCd());
        }
    }


}
