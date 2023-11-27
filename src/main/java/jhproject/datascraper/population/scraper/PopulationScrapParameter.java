package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.entity.PopulationScrapLog;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
public class PopulationScrapParameter {
    public static final int[] lvs = {1, 2, 3, 4};
    public static int[] regSeCds = {1, 2, 3, 4};

    private final String yearMonth;
    private final String stdgCd;
    private final int lv;
    private final int regSeCd;
    private final int pageNo;


    public PopulationScrapParameter(String yearMonth, String stdgCd, int lv, int regSeCd, int pageNo) {
        this.yearMonth = yearMonth;
        this.stdgCd = stdgCd;
        this.lv = lv;
        this.regSeCd = regSeCd;
        this.pageNo = pageNo;
    }

    public PopulationScrapParameter(PopulationScrapLog populationScrapLog) {
        this.yearMonth = populationScrapLog.getYearMonth();
        this.stdgCd = populationScrapLog.getStdgCd();
        this.lv = populationScrapLog.getLv();
        this.regSeCd = populationScrapLog.getRegSeCd();
        this.pageNo = 1;
    }

    public static PopulationScrapParameter first() {
        return PopulationScrapParameter.firstOf(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
    }


    public Optional<PopulationScrapParameter> next(List<Population> currentResults) {
        if (!hasNextLv() & !hasNextRegSeCd()) {
            var nextMonthParameter = PopulationScrapParameter.firstOf(PopulationScrapYearMonth.of(yearMonth).nextMonth());
            return Optional.of(nextMonthParameter);
        }

        Optional<Population> resultByThis = currentResults.stream()
                .filter(p -> p.getLv() == this.lv && p.getRegSeCd() == this.regSeCd && p.getStdgCd().equals(this.stdgCd))
                .findFirst();

        List<Population> sortedList = currentResults.stream()
                .sorted(Comparator.comparing(Population::getLv))
                .sorted(Comparator.comparing(Population::getStdgCd))
                .toList();

        if (resultByThis.isPresent()) {
            Population nextTarget = sortedList.get(sortedList.indexOf(resultByThis.get()) + 1);
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


    public static PopulationScrapParameter firstOf(@NonNull PopulationScrapYearMonth yearMonth) {
        return new PopulationScrapParameter(
                yearMonth.getYearMonth(),
                "1000000000",
                1,
                1,
                1
        );
    }

    public PopulationScrapParameter nextPage() {
        return new PopulationScrapParameter(
                this.yearMonth,
                this.stdgCd,
                this.lv,
                this.regSeCd,
                this.pageNo + 1
        );
    }

    public boolean hasNextLv() {
        return Arrays.stream(lvs)
                .filter(lv -> lv > this.lv)
                .count() > 0;
    }

    public List<PopulationScrapParameter> getNextLvParameters(List<PopulationScrapData> results) {
        if (CollectionUtils.isEmpty(results) || !hasNextLv()) {
            return List.of();
        }

        return results.stream()
                .map(p -> new PopulationScrapParameter(
                        this.yearMonth,
                        p.getStdgCd(),
                        this.lv + 1,
                        this.regSeCd,
                        1
                ))
                .toList();
    }

    public PublicDataPopulationGetParameter toPublicDataPopulationGetParameter(String serviceKey) {
        return new PublicDataPopulationGetParameter(
                serviceKey,
                this.stdgCd,
                this.yearMonth,
                this.yearMonth,
                String.valueOf(this.lv),
                String.valueOf(this.regSeCd),
                "JSON",
                "1000",
                this.pageNo
        );
    }

    public boolean hasNextRegSeCd() {
        return Arrays.stream(regSeCds)
                .filter(regSeCd -> regSeCd > this.regSeCd)
                .count() > 0;
    }

    public Optional<PopulationScrapParameter> getNextRegSeCdParameter() {
        if (!hasNextRegSeCd()) {
            return Optional.empty();
        }

        return Optional.of(new PopulationScrapParameter(
                this.yearMonth,
                this.stdgCd,
                this.lv,
                this.regSeCd + 1,
                1
        ));
    }

}
