package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PublicDataPopulationGetResponse;
import lombok.Getter;

@Getter
public class PopulationScrapData {
    private final String yyyyMm;
    private final String regSeCd;
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


    public PopulationScrapData(PopulationScrapParameter parameter, PublicDataPopulationGetResponse.Item responseItem) {
        this.yyyyMm = parameter.getYearMonth();
        this.regSeCd = String.valueOf(parameter.getRegSeCd());
        this.ctpvNm = responseItem.getCtpvNm();
        this.dongNm = responseItem.getDongNm();
        this.tong = responseItem.getTong();
        this.ban = responseItem.getBan();
        this.liNm = responseItem.getLiNm();
        this.stdgCd = responseItem.getStdgCd();
        this.stdgNm = responseItem.getStdgNm();
        this.sggNm = responseItem.getSggNm();
        this.admmCd = responseItem.getAdmmCd();
        this.male0AgeNmprCnt = Integer.parseInt(responseItem.getMale0AgeNmprCnt());
        this.feml0AgeNmprCnt = Integer.parseInt(responseItem.getFeml0AgeNmprCnt());
        this.male10AgeNmprCnt = Integer.parseInt(responseItem.getMale10AgeNmprCnt());
        this.feml10AgeNmprCnt = Integer.parseInt(responseItem.getFeml10AgeNmprCnt());
        this.male20AgeNmprCnt = Integer.parseInt(responseItem.getMale20AgeNmprCnt());
        this.feml20AgeNmprCnt = Integer.parseInt(responseItem.getFeml20AgeNmprCnt());
        this.male30AgeNmprCnt = Integer.parseInt(responseItem.getMale30AgeNmprCnt());
        this.feml30AgeNmprCnt = Integer.parseInt(responseItem.getFeml30AgeNmprCnt());
        this.male40AgeNmprCnt = Integer.parseInt(responseItem.getMale40AgeNmprCnt());
        this.feml40AgeNmprCnt = Integer.parseInt(responseItem.getFeml40AgeNmprCnt());
        this.male50AgeNmprCnt = Integer.parseInt(responseItem.getMale50AgeNmprCnt());
        this.feml50AgeNmprCnt = Integer.parseInt(responseItem.getFeml50AgeNmprCnt());
        this.male60AgeNmprCnt = Integer.parseInt(responseItem.getMale60AgeNmprCnt());
        this.feml60AgeNmprCnt = Integer.parseInt(responseItem.getFeml60AgeNmprCnt());
        this.male70AgeNmprCnt = Integer.parseInt(responseItem.getMale70AgeNmprCnt());
        this.feml70AgeNmprCnt = Integer.parseInt(responseItem.getFeml70AgeNmprCnt());
        this.male80AgeNmprCnt = Integer.parseInt(responseItem.getMale80AgeNmprCnt());
        this.feml80AgeNmprCnt = Integer.parseInt(responseItem.getFeml80AgeNmprCnt());
        this.male90AgeNmprCnt = Integer.parseInt(responseItem.getMale90AgeNmprCnt());
        this.feml90AgeNmprCnt = Integer.parseInt(responseItem.getFeml90AgeNmprCnt());
        this.male100AgeNmprCnt = Integer.parseInt(responseItem.getMale100AgeNmprCnt());
        this.feml100AgeNmprCnt = Integer.parseInt(responseItem.getFeml100AgeNmprCnt());
        this.maleNmprCnt = Integer.parseInt(responseItem.getMaleNmprCnt());
        this.femlNmprCnt = Integer.parseInt(responseItem.getFemlNmprCnt());
        this.totNmprCnt = Integer.parseInt(responseItem.getTotNmprCnt());
        this.statsYm = responseItem.getStatsYm();
    }

    public Population toEntity() {
        return new Population(
                yyyyMm,
                regSeCd,
                ctpvNm,
                dongNm,
                tong,
                ban,
                liNm,
                stdgCd,
                stdgNm,
                sggNm,
                admmCd,
                male0AgeNmprCnt,
                feml0AgeNmprCnt,
                male10AgeNmprCnt,
                feml10AgeNmprCnt,
                male20AgeNmprCnt,
                feml20AgeNmprCnt,
                male30AgeNmprCnt,
                feml30AgeNmprCnt,
                male40AgeNmprCnt,
                feml40AgeNmprCnt,
                male50AgeNmprCnt,
                feml50AgeNmprCnt,
                male60AgeNmprCnt,
                feml60AgeNmprCnt,
                male70AgeNmprCnt,
                feml70AgeNmprCnt,
                male80AgeNmprCnt,
                feml80AgeNmprCnt,
                male90AgeNmprCnt,
                feml90AgeNmprCnt,
                male100AgeNmprCnt,
                feml100AgeNmprCnt,
                maleNmprCnt,
                femlNmprCnt,
                totNmprCnt,
                statsYm
        );
    }

}
