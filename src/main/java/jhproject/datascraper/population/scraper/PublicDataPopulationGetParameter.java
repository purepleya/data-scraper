package jhproject.datascraper.population.scraper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublicDataPopulationGetParameter {
        private String serviceKey;
        private String stdgCd;
        private String srchFrYm;
        private String srchToYm;
        private String lv;
        private String regSeCd;
        private String type;
        private String numOfRows;
        private Integer pageNo;

    public PublicDataPopulationGetParameter(String serviceKey, String stdgCd, String srchFrYm, String srchToYm, String lv, String regSeCd, String type, String numOfRows, Integer pageNo) {
        this.serviceKey = serviceKey;
        this.stdgCd = stdgCd;
        this.srchFrYm = srchFrYm;
        this.srchToYm = srchToYm;
        this.lv = lv;
        this.regSeCd = regSeCd;
        this.type = type;
        this.numOfRows = numOfRows;
        this.pageNo = pageNo;
    }

}
