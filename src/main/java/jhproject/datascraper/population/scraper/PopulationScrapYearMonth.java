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
        if (!isValidate(yearMonth)) {
            throw new IllegalArgumentException("유효하지 않은 년월(" + yearMonth.format(DateTimeFormatter.ISO_DATE) + ")입니다.");
        }

        this.yearMonth = yearMonth;
    }

    private boolean isValidate(LocalDate yearMonth) {
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

    public boolean isLastMonth() {
        return getYearMonth().equals(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMM")));
    }
}