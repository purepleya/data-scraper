package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.repository.PopulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NextPopulationScrapParameterBuilder {

    private final PopulationRepository populationRepository;

    public PopulationScrapParameter build(PopulationScrapParameter currentParameter) {
//        TODO 구현
//        현재 파라미터와 YearMonth, RegSeCd가 같고, Lv가 같거나 큰 결과를 조회한다.
        List<Population> currentResults = populationRepository.findByYearMonthAndRegSeCdAndGoeLv(currentParameter.getYearMonth(), currentParameter.getRegSeCd(), currentParameter.getLv());

//        결과 중에서 현재 파라미터의 결과 값을 찾는다.
        Optional<Population> resultByThis = currentResults.stream()
                .filter(p -> p.getLv() == this.lv && p.getRegSeCd() == this.regSeCd && p.getStdgCd().equals(this.stdgCd))
                .findFirst();

//        결과를 lv, stdgCd 순으로 정렬한다.
        List<Population> sortedList = current2LvResults.stream()
                .sorted(Comparator.comparing(Population::getLv))
                .sorted(Comparator.comparing(Population::getStdgCd))
                .toList();

        if (resultByThis.isPresent()) {
            int indexOfResultByThis = sortedList.indexOf(resultByThis.get());

            if (indexOfResultByThis == sortedList.size() - 1) {
//                모든 지역을 다 scrap 한경우

                if (hasNextRegSeCd()) {
//                    아직 더 처리할 regSeCd가 남은 경우
                    return Optional.of(new PopulationScrapParameter(
                            this.yearMonth,
                            PopulationScrapParameter.first().getStdgCd(),
                            PopulationScrapParameter.first().getLv(),
                            this.regSeCd + 1,
                            1
                    ));
                }
            }


            Population nextTarget = sortedList.get(indexOfResultByThis + 1);
            return Optional.of(new PopulationScrapParameter(
                    this.yearMonth,
                    nextTarget.getStdgCd(),
                    nextTarget.getLv(),
                    this.regSeCd,
                    1
            ));
        } else {

            return Optional.of(new PopulationScrapParameter(
                    this.yearMonth,
                    sortedList.get(0).getStdgCd(),
                    sortedList.get(0).getLv(),
                    this.regSeCd,
                    1
            ));
        }
    }

}
