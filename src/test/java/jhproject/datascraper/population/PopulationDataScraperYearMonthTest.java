package jhproject.datascraper.population;

import jhproject.datascraper.population.PopulationDataScraper.PopulationDataScraperYearMonth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PopulationDataScraperYearMonthTest {

    @Test
    @DisplayName("null Argument로 생성하면 NullPointerException이 발생한다.")
    void of_shouldThrowException_whenNullArgument() {
        assertThrows(NullPointerException.class, () -> PopulationDataScraperYearMonth.of(null));
    }

    @Test
    @DisplayName("최초 수집 년월은 202210 이다.")
    void firstYearMonth_shouldBe_202210() {
        assertEquals(PopulationDataScraperYearMonth.of(LocalDate.of(2022, 10, 1)), PopulationDataScraperYearMonth.FIRST_YEAR_MONTH);
    }

    @Test
    @DisplayName("getYearMonth() 메서드는 yyyyMM 형식의 String을 리턴한다.")
    void getYearMonth_shouldReturn_yyyyMM() {
        PopulationDataScraperYearMonth populationDataScraperYearMonth = PopulationDataScraperYearMonth.of(LocalDate.of(2023, 4, 1));
        assertEquals("202304", populationDataScraperYearMonth.getYearMonth());
    }
    
    @Test
    @DisplayName("nextMonth() 메서드는 다음 달을 리턴한다.")
    void nextMonth_shouldReturn_nextMonth() {
        PopulationDataScraperYearMonth populationDataScraperYearMonth = PopulationDataScraperYearMonth.of(LocalDate.of(2023, 4, 1));
        assertEquals(PopulationDataScraperYearMonth.of(LocalDate.of(2023, 5, 1)), populationDataScraperYearMonth.nextMonth());
    }

    @Test
    @DisplayName("최초 수집월(FIRST_YEAR_MONTH) 이전 날짜는 유효성 오류가 발생한다.")
    void isValidate_shouldThrowException_whenBeforeFirstYearMonth() {
        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.of(2022, 9, 1)));
//        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.now()));
//        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.now().plusMonths(1)));
//        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.now().minusMonths(1)).nextMonth());
    }

    @Test
    @DisplayName("현재 년월 이후 날짜는 유효성 오류가 발생한다.")
    void isValidate_shouldThrowException_whenAfterCurrentYearMonth() {
        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.now().plusMonths(1)));
    }

    @Test
    @DisplayName("nextMont() 메서드로 유효하지 않은 달이 생성되면 유효성 오류가 발생한다.")
    void isValidate_shouldThrowException_whenNextMonthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> PopulationDataScraperYearMonth.of(LocalDate.now().minusMonths(1)).nextMonth());
    }

}