package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.entity.PopulationScrapLog;
import jhproject.datascraper.population.repository.PopulationRepository;
import jhproject.datascraper.population.repository.PopulationScrapLogRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScrapYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PopulationScrapParameterGenerator {

    private final PopulationRepository populationRepository;
    private final PopulationScrapLogRepository populationScrapLogRepository;


    public Optional<PopulationScrapParameter> generate() {
        Optional<PopulationScrapLog> lastLogOptional = populationScrapLogRepository.getLastLog();

        if (lastLogOptional.isEmpty()) {
            return Optional.of(PopulationScrapParameter.first());
        }

        PopulationScrapParameter lastParameter = new PopulationScrapParameter(lastLogOptional.get());
        Optional<PopulationScrapParameter> nextParameterOptional = generateNext(lastParameter);

        return nextParameterOptional;
    }


    private Optional<PopulationScrapParameter> generateNext(PopulationScrapParameter currentParameter) {
//        현재 파라미터와 YearMonth, RegSeCd가 같고, Lv가 같거나 큰 결과를 조회한다.
        List<Population> currentResults = populationRepository.findByYearMonthAndRegSeCdAndGoeLv(currentParameter.getYearMonth(), currentParameter.getRegSeCd(), currentParameter.getLv());

        if (CollectionUtils.isEmpty(currentResults)) {
//            조회 결과가 없다면 현재 파라미터의 다음 regSeCd로 넘어간다.
            return Optional.of(buildNextRegSeCdFirstParameter(currentParameter));
        }

//        결과 중에서 현재 파라미터의 결과 값을 찾는다.
        Optional<Population> resultByThis = currentResults.stream()
                .filter(p -> Objects.equals(p.getLv(), currentParameter.getLv())
                        && Objects.equals(p.getRegSeCd(), currentParameter.getRegSeCd())
                        && Objects.equals(p.getStdgCd(), currentParameter.getStdgCd()))
                .findFirst();

//        결과를 lv, stdgCd 순으로 정렬한다.
        List<Population> sortedList = currentResults.stream()
                .sorted(Comparator.comparing(Population::getLv))
                .sorted(Comparator.comparing(Population::getStdgCd))
                .toList();


        if (resultByThis.isPresent()) {
            int indexOfResultByThis = sortedList.indexOf(resultByThis.get());

            if (indexOfResultByThis == sortedList.size() - 1) {
//                모든 지역을 다 scrap 한경우

                if (currentParameter.hasNextRegSeCd()) {
//                    아직 더 처리할 regSeCd가 남아 있다면 다음 regSeCd로 넘어간다.
                    return Optional.of(buildNextRegSeCdFirstParameter(currentParameter));
                } else {
//                    모든 regSeCd를 다 scrap 한 경우 다음 달로 넘어간다.
                    return Optional.of(buildNextMonthFirstParameter(currentParameter.getYearMonth()));
                }
            } else {
//                다음 Scrap 할 지역을 골라서 (lv, stdgCd 순으로 정렬되어 있으므로) 그 값을 기준으로 다음 파라미터를 생성한다.
                Population nextTarget = sortedList.get(indexOfResultByThis + 1);
                return Optional.of(buildParameter(currentParameter, nextTarget));
            }

        } else {
//            혹시 결과 중에서 현재 파라미터의 결과 값이 없다면 결과의 제일 첫번째 값을 기준으로 다음 파라미터를 생성한다.
            return Optional.of(buildParameter(currentParameter, sortedList.get(0)));
        }
    }


    private PopulationScrapParameter buildParameter(PopulationScrapParameter currentParameter, Population nextTarget) {
        return new PopulationScrapParameter(
                currentParameter.getYearMonth(),
                nextTarget.getStdgCd(),
                nextTarget.getLv(),
                currentParameter.getRegSeCd(),
                1
        );
    }

    private PopulationScrapParameter buildNextMonthFirstParameter(String currentYearMonth) {
        PopulationScrapYearMonth populationScrapYearMonth = PopulationScrapYearMonth.of(currentYearMonth);

        return new PopulationScrapParameter(
                populationScrapYearMonth.nextMonth().getYearMonth(),
                PopulationScrapParameter.first().getStdgCd(),
                PopulationScrapParameter.first().getLv(),
                PopulationScrapParameter.first().getRegSeCd(),
                1
        );
    }

    private PopulationScrapParameter buildNextRegSeCdFirstParameter(PopulationScrapParameter currentParameter) {
        if (currentParameter.hasNextRegSeCd()) {
            return new PopulationScrapParameter(
                    currentParameter.getYearMonth(),
                    PopulationScrapParameter.first().getStdgCd(),
                    PopulationScrapParameter.first().getLv(),
                    currentParameter.getRegSeCd() + 1,
                    1
            );
        } else {
//            현재 파라미터의 regSeCd가 마지막이라면 다음 달로 넘어간다.
            return buildNextMonthFirstParameter(currentParameter.getYearMonth());
        }
    }

}
