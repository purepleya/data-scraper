package jhproject.datascraper.population.scraper;

import lombok.NonNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PopulationScrapYearMonth {
    private static final LocalDate FIRST_DATE = LocalDate.of(2022, 10, 1);
    public static final PopulationScrapYearMonth FIRST_YEAR_MONTH = new PopulationScrapYearMonth(FIRST_DATE);
    public final LocalDate yearMonth;

    public static PopulationScrapYearMonth of(@NonNull LocalDate yearMonth) {
        return new PopulationScrapYearMonth(yearMonth);
    }

    private PopulationScrapYearMonth(LocalDate yearMonth) {
        this.yearMonth = yearMonth;
    }

    public boolean isValid() {
        return !(yearMonth.isBefore(PopulationScrapYearMonth.FIRST_DATE) || yearMonth.isAfter(LocalDate.now().withDayOfMonth(1).minusDays(1)));
    }

    public String getYearMonth() {
        return yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopulationScrapYearMonth that = (PopulationScrapYearMonth) o;
        return Objects.equals(getYearMonth(), that.getYearMonth());
    }

    public PopulationScrapYearMonth nextMonth() {
        return new PopulationScrapYearMonth(yearMonth.plusMonths(1));
    }

}