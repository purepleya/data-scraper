package jhproject.datascraper.population;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public class PopulationDataScraper {



    public List<Population> scrap(LocalDate currentMonth) {
//        currentMonth가 유효한 월인지(DB에 해당 월 데이터가 있는지, 이번달 보다 이후는 아닌지) 확인
//        공공데이터 API 호출해서 데이터 수집

        return List.of();
    }


    public static class PopulationScrapYearMonth {
//        TODO: isValidate 메서드
//        TODO: getYearMonth 메서드(String 타입 yyyyMM 리턴)
        private final LocalDate yearMonth;

        public static PopulationScrapYearMonth of(@NonNull LocalDate yearMonth) {
            return new PopulationScrapYearMonth(yearMonth);
        }

        private PopulationScrapYearMonth(LocalDate yearMonth) {
            this.yearMonth = yearMonth;
        }


    }

}
