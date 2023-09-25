package jhproject.datascraper.population;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class PopulationDataScraper {



    public List<Population> scrap(LocalDate currentMonth) {
//        currentMonth가 유효한 월인지(DB에 해당 월 데이터가 있는지, 이번달 보다 이후는 아닌지) 확인
//        공공데이터 API 호출해서 데이터 수집

        return List.of();
    }




    public static class PopulationDataScraperYearMonth {
        private static final LocalDate FIRST_DATE = LocalDate.of(2022, 10, 1);
        public static final PopulationDataScraperYearMonth FIRST_YEAR_MONTH = new PopulationDataScraperYearMonth(FIRST_DATE);

        private final LocalDate yearMonth;

        public static PopulationDataScraperYearMonth of(@NonNull LocalDate yearMonth) {
            return new PopulationDataScraperYearMonth(yearMonth);
        }

        private PopulationDataScraperYearMonth(LocalDate yearMonth) {
            if (yearMonth.isBefore(FIRST_DATE) || yearMonth.isAfter(LocalDate.now().withDayOfMonth(1).minusDays(1))) {
                throw new IllegalArgumentException("유효하지 않은 년월(" + yearMonth.format(DateTimeFormatter.ISO_DATE) + ")입니다.");
            }

            this.yearMonth = yearMonth;
        }

        public String getYearMonth() {
            return yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PopulationDataScraperYearMonth that = (PopulationDataScraperYearMonth) o;
            return Objects.equals(getYearMonth(), that.getYearMonth());
        }

        public PopulationDataScraperYearMonth nextMonth() {
            return new PopulationDataScraperYearMonth(yearMonth.plusMonths(1));
        }
    }

}
