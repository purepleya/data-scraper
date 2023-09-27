package jhproject.datascraper.population.scraper;

public record PublicDataPopulationGetParameter(
        String serviceKey,
        String stdgCd,
        String srchFrYm,
        String srchToYm,
        String lv,
        String regSeCd,
        String type,
        String numOfRows,
        String pageNo
) {
}
