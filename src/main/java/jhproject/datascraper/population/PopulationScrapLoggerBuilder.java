package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
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

    public PopulationScrapLogger build(PopulationScrapYearMonth yearMonth) {
        return new PopulationScrapLogger(yearMonth.yearMonth.getYear(), yearMonth.yearMonth.getMonthValue(), populationScrapLogRepository);
    }


    @Slf4j
    public static class PopulationScrapLogger {
        private final int year;
        private final int month;
        private final PopulationScrapLogRepository populationScrapLogRepository;
        private PopulationScrapLog logEntity;

        private PopulationScrapLogger(int year, int month, PopulationScrapLogRepository populationScrapLogRepository) {
            this.year = year;
            this.month = month;
            this.populationScrapLogRepository = populationScrapLogRepository;
        }

        @Transactional
        public void start() {
            log.info("Population scrap start. year: {}, month: {}", year, month);
            PopulationScrapLog newLog = new PopulationScrapLog(null, year, month, LocalDateTime.now(), null);
            this.logEntity = populationScrapLogRepository.save(newLog);
        }

        @Transactional
        public void end() {
            if (Objects.nonNull(this.logEntity)) {
                this.logEntity.setEndAt(LocalDateTime.now());
                populationScrapLogRepository.save(this.logEntity);
                log.info("Population scrap end. year: {}, month: {}", year, month);
            } else {
                PopulationScrapLog newEndLog = new PopulationScrapLog(null, year, month, null, LocalDateTime.now());
                this.logEntity = populationScrapLogRepository.save(newEndLog);
            }
        }
    }


}
