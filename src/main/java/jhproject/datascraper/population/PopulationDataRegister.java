package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PopulationDataRegister {

    @Transactional
    public void save(PopulationScrapYearMonth yearMonth, List<Population> populations) {
//        TODO DB에 데이터 등록


    }

}
