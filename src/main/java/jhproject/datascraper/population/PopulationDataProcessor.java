package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScrapNextYearMonthFinder populationScrapNextYearMonthFinder;
    private final PopulationScraper scraper;

    public void run() {
//        TODO 마지막 데이터 수집일을 조회해서 Scrap을 실행할 달을 결정
        Optional<PopulationScrapYearMonth> yearMonthOptional = populationScrapNextYearMonthFinder.find();

//        TODO Scraper를 이용해서 데이터 조회
        List<Population> populations = scraper.scrap(yearMonthOptional.get());

//        TODO DB에 데이터 등록 및 로그 처리
//        populationDataRegister.save(yearMonth, populations);
    }



}
