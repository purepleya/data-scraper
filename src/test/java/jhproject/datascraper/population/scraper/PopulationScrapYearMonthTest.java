package jhproject.datascraper.population.scraper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PopulationScrapYearMonthTest {

    @Test
    @DisplayName("null Argument로 생성하면 NullPointerException이 발생한다.")
    void of_shouldThrowException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> PopulationScrapYearMonth.of(null));
    }

    @Test
    @DisplayName("최초 수집 년월은 202210 이다.")
    void firstYearMonth_shouldBe_202210() {
        assertEquals(PopulationScrapYearMonth.of(LocalDate.of(2022, 10, 1)), PopulationScrapYearMonth.FIRST_YEAR_MONTH);
    }

    @Test
    @DisplayName("getYearMonth() 메서드는 yyyyMM 형식의 String을 리턴한다.")
    void getYearMonth_shouldReturn_yyyyMM() {
        PopulationScrapYearMonth populationDataScraperYearMonth = PopulationScrapYearMonth.of(LocalDate.of(2023, 4, 1));
        assertEquals("202304", populationDataScraperYearMonth.getYearMonth());
    }
    
    @Test
    @DisplayName("nextMonth() 메서드는 다음 달을 리턴한다.")
    void nextMonth_shouldReturn_nextMonth() {
        PopulationScrapYearMonth populationDataScraperYearMonth = PopulationScrapYearMonth.of(LocalDate.of(2023, 4, 1));
        assertEquals(PopulationScrapYearMonth.of(LocalDate.of(2023, 5, 1)), populationDataScraperYearMonth.nextMonth());
    }

    @Test
    @DisplayName("최초 수집 년월(2022년 10월) 이전 달은 invalid 하다.")
    void isValidate_shouldReturn_false_whenBeforeFirstYearMonth() {
        assertFalse(PopulationScrapYearMonth.of(LocalDate.of(2022, 9, 1)).isValid());
    }

    @Test
    @DisplayName("현재 년월 이후 달은 invalid 하다.")
    void isValidate_shouldReturn_false_whenAfterCurrentYearMonth() {
        assertFalse(PopulationScrapYearMonth.of(LocalDate.now().plusMonths(1)).isValid());
    }


}