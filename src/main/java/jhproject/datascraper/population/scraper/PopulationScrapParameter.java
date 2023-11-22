package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
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


    public static PopulationScrapParameter first() {
        return PopulationScrapParameter.firstOf(PopulationScrapYearMonth.FIRST_YEAR_MONTH);
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

    public Optional<PopulationScrapParameter> getNextRegSeCd() {
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
