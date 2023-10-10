package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScraper scraper;

    public void run() {
//        TODO 마지막 데이터 수집일을 조회해서 Scrap을 실행할 달을 결정
        PopulationScrapYearMonth yearMonth = populationScrapNextYearMonthFinder.find();

//        TODO Scraper를 이용해서 데이터 조회
        List<Population> populations = scraper.scrap(yearMonth);

//        TODO DB에 데이터 등록 및 로그 처리
        populationDataRegister.save(yearMonth, populations);
    }



}
