package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PublicDataPopulationGetResponse;
import lombok.Getter;

@Getter
public class Population {
    private final String ctpvNm;
    private final String dongNm;
    private final String tong;
    private final String ban;
    private final String liNm;
    private final String stdgCd;
    private final String stdgNm;
    private final String sggNm;
    private final String admmCd;
    private final Integer male0AgeNmprCnt;
    private final Integer feml0AgeNmprCnt;
    private final Integer male10AgeNmprCnt;
    private final Integer feml10AgeNmprCnt;
    private final Integer male20AgeNmprCnt;
    private final Integer feml20AgeNmprCnt;
    private final Integer male30AgeNmprCnt;
    private final Integer feml30AgeNmprCnt;
    private final Integer male40AgeNmprCnt;
    private final Integer feml40AgeNmprCnt;
    private final Integer male50AgeNmprCnt;
    private final Integer feml50AgeNmprCnt;
    private final Integer male60AgeNmprCnt;
    private final Integer feml60AgeNmprCnt;
    private final Integer male70AgeNmprCnt;
    private final Integer feml70AgeNmprCnt;
    private final Integer male80AgeNmprCnt;
    private final Integer feml80AgeNmprCnt;
    private final Integer male90AgeNmprCnt;
    private final Integer feml90AgeNmprCnt;
    private final Integer male100AgeNmprCnt;
    private final Integer feml100AgeNmprCnt;
    private final Integer maleNmprCnt;
    private final Integer femlNmprCnt;
    private final Integer totNmprCnt;
    private final String statsYm;


    public Population(PublicDataPopulationGetResponse.Item responseItem) {
        this.ctpvNm = responseItem.ctpvNm();
        this.dongNm = responseItem.dongNm();
        this.tong = responseItem.tong();
        this.ban = responseItem.ban();
        this.liNm = responseItem.liNm();
        this.stdgCd = responseItem.stdgCd();
        this.stdgNm = responseItem.stdgNm();
        this.sggNm = responseItem.sggNm();
        this.admmCd = responseItem.admmCd();
        this.male0AgeNmprCnt = responseItem.male0AgeNmprCnt();
        this.feml0AgeNmprCnt = responseItem.feml0AgeNmprCnt();
        this.male10AgeNmprCnt = responseItem.male10AgeNmprCnt();
        this.feml10AgeNmprCnt = responseItem.feml10AgeNmprCnt();
        this.male20AgeNmprCnt = responseItem.male20AgeNmprCnt();
        this.feml20AgeNmprCnt = responseItem.feml20AgeNmprCnt();
        this.male30AgeNmprCnt = responseItem.male30AgeNmprCnt();
        this.feml30AgeNmprCnt = responseItem.feml30AgeNmprCnt();
        this.male40AgeNmprCnt = responseItem.male40AgeNmprCnt();
        this.feml40AgeNmprCnt = responseItem.feml40AgeNmprCnt();
        this.male50AgeNmprCnt = responseItem.male50AgeNmprCnt();
        this.feml50AgeNmprCnt = responseItem.feml50AgeNmprCnt();
        this.male60AgeNmprCnt = responseItem.male60AgeNmprCnt();
        this.feml60AgeNmprCnt = responseItem.feml60AgeNmprCnt();
        this.male70AgeNmprCnt = responseItem.male70AgeNmprCnt();
        this.feml70AgeNmprCnt = responseItem.feml70AgeNmprCnt();
        this.male80AgeNmprCnt = responseItem.male80AgeNmprCnt();
        this.feml80AgeNmprCnt = responseItem.feml80AgeNmprCnt();
        this.male90AgeNmprCnt = responseItem.male90AgeNmprCnt();
        this.feml90AgeNmprCnt = responseItem.feml90AgeNmprCnt();
        this.male100AgeNmprCnt = responseItem.male100AgeNmprCnt();
        this.feml100AgeNmprCnt = responseItem.feml100AgeNmprCnt();
        this.maleNmprCnt = responseItem.maleNmprCnt();
        this.femlNmprCnt = responseItem.femlNmprCnt();
        this.totNmprCnt = responseItem.totNmprCnt();
        this.statsYm = responseItem.statsYm();
    }

}
