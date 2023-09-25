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
    @DisplayName("최초 수집월(FIRST_YEAR_MONTH) 이전 날짜는 유효성 오류가 발생한다.")
    void isValidate_shouldThrowException_whenBeforeFirstYearMonth() {
        assertThrows(IllegalArgumentException.class, () -> PopulationScrapYearMonth.of(LocalDate.of(2022, 9, 1)));
    }

    @Test
    @DisplayName("현재 년월 이후 날짜는 유효성 오류가 발생한다.")
    void isValidate_shouldThrowException_whenAfterCurrentYearMonth() {
        assertThrows(IllegalArgumentException.class, () -> PopulationScrapYearMonth.of(LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> PopulationScrapYearMonth.of(LocalDate.now().plusMonths(1)));
    }

    @Test
    @DisplayName("nextMonth() 메서드로 유효하지 않은 달이 생성되면 유효성 오류가 발생한다.")
    void isValidate_shouldThrowException_whenNextMonthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> PopulationScrapYearMonth.of(LocalDate.now().minusMonths(1)).nextMonth());
    }

    @Test
    @DisplayName("수집할 수 있는 마지막 달 일경우 isLastMonth() 메서드는 true를 리턴한다.")
    void isLastMonth_shouldReturn_true_whenLastMonth() {
        assertTrue(PopulationScrapYearMonth.of(LocalDate.now().minusMonths(1)).isLastMonth());
        assertFalse(PopulationScrapYearMonth.of(LocalDate.now().minusMonths(2)).isLastMonth());
    }


}