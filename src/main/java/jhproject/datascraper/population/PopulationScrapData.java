package jhproject.datascraper.population;

import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PublicDataPopulationGetResponse;
import lombok.Getter;

import java.util.Objects;

@Getter
public class PopulationScrapData {
    private final String yyyyMm;
    private final Integer regSeCd;
    private final String ctpvNm;
    private final String dongNm;
    private final String tong;
    private final String ban;
    private final String liNm;
    private final Integer lv;
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
        this.regSeCd = parameter.getRegSeCd();
        this.ctpvNm = responseItem.getCtpvNm();
        this.dongNm = responseItem.getDongNm();
        this.tong = responseItem.getTong();
        this.ban = responseItem.getBan();
        this.liNm = responseItem.getLiNm();
        this.lv = parameter.getLv() + 1;
        this.stdgCd = responseItem.getStdgCd();
        this.stdgNm = responseItem.getStdgNm();
        this.sggNm = responseItem.getSggNm();
        this.admmCd = responseItem.getAdmmCd();
        this.male0AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale0AgeNmprCnt());
        this.feml0AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml0AgeNmprCnt());
        this.male10AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale10AgeNmprCnt());
        this.feml10AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml10AgeNmprCnt());
        this.male20AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale20AgeNmprCnt());
        this.feml20AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml20AgeNmprCnt());
        this.male30AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale30AgeNmprCnt());
        this.feml30AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml30AgeNmprCnt());
        this.male40AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale40AgeNmprCnt());
        this.feml40AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml40AgeNmprCnt());
        this.male50AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale50AgeNmprCnt());
        this.feml50AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml50AgeNmprCnt());
        this.male60AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale60AgeNmprCnt());
        this.feml60AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml60AgeNmprCnt());
        this.male70AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale70AgeNmprCnt());
        this.feml70AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml70AgeNmprCnt());
        this.male80AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale80AgeNmprCnt());
        this.feml80AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml80AgeNmprCnt());
        this.male90AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale90AgeNmprCnt());
        this.feml90AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml90AgeNmprCnt());
        this.male100AgeNmprCnt = parseNullToZeroElseInt(responseItem.getMale100AgeNmprCnt());
        this.feml100AgeNmprCnt = parseNullToZeroElseInt(responseItem.getFeml100AgeNmprCnt());
        this.maleNmprCnt = parseNullToZeroElseInt(responseItem.getMaleNmprCnt());
        this.femlNmprCnt = parseNullToZeroElseInt(responseItem.getFemlNmprCnt());
        this.totNmprCnt = parseNullToZeroElseInt(responseItem.getTotNmprCnt());
        this.statsYm = responseItem.getStatsYm();
    }

    private Integer parseNullToZeroElseInt(String value) {
        return Objects.isNull(value) ? 0 : Integer.parseInt(value);
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
                lv,
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
