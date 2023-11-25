package jhproject.datascraper.population.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "population")
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "yyyy_mm")
    private String yyyyMm;

    @Column(name = "reg_se_cd")
    private Integer regSeCd;

    @Column(name = "ctpv_nm")
    private String ctpvNm;

    @Column(name = "dong_nm")
    private String dongNm;

    @Column(name = "tong")
    private String tong;

    @Column(name = "ban")
    private String ban;

    @Column(name = "li_nm")
    private String liNm;

    @Column(name = "lv")
    private Integer lv;

    @Column(name = "stdg_cd")
    private String stdgCd;

    @Column(name = "stdg_nm")
    private String stdgNm;

    @Column(name = "sgg_nm")
    private String sggNm;

    @Column(name = "admm_cd")
    private String admmCd;

    @Column(name = "male_0_age_nmpr_cnt")
    private Integer male0AgeNmprCnt;

    @Column(name = "feml_0_age_nmpr_cnt")
    private Integer feml0AgeNmprCnt;

    @Column(name = "male_10_age_nmpr_cnt")
    private Integer male10AgeNmprCnt;

    @Column(name = "feml_10_age_nmpr_cnt")
    private Integer feml10AgeNmprCnt;

    @Column(name = "male_20_age_nmpr_cnt")
    private Integer male20AgeNmprCnt;

    @Column(name = "feml_20_age_nmpr_cnt")
    private Integer feml20AgeNmprCnt;

    @Column(name = "male_30_age_nmpr_cnt")
    private Integer male30AgeNmprCnt;

    @Column(name = "feml_30_age_nmpr_cnt")
    private Integer feml30AgeNmprCnt;

    @Column(name = "male_40_age_nmpr_cnt")
    private Integer male40AgeNmprCnt;

    @Column(name = "feml_40_age_nmpr_cnt")
    private Integer feml40AgeNmprCnt;

    @Column(name = "male_50_age_nmpr_cnt")
    private Integer male50AgeNmprCnt;

    @Column(name = "feml_50_age_nmpr_cnt")
    private Integer feml50AgeNmprCnt;

    @Column(name = "male_60_age_nmpr_cnt")
    private Integer male60AgeNmprCnt;

    @Column(name = "feml_60_age_nmpr_cnt")
    private Integer feml60AgeNmprCnt;

    @Column(name = "male_70_age_nmpr_cnt")
    private Integer male70AgeNmprCnt;

    @Column(name = "feml_70_age_nmpr_cnt")
    private Integer feml70AgeNmprCnt;

    @Column(name = "male_80_age_nmpr_cnt")
    private Integer male80AgeNmprCnt;

    @Column(name = "feml_80_age_nmpr_cnt")
    private Integer feml80AgeNmprCnt;

    @Column(name = "male_90_age_nmpr_cnt")
    private Integer male90AgeNmprCnt;

    @Column(name = "feml_90_age_nmpr_cnt")
    private Integer feml90AgeNmprCnt;

    @Column(name = "male_100_age_nmpr_cnt")
    private Integer male100AgeNmprCnt;

    @Column(name = "feml_100_age_nmpr_cnt")
    private Integer feml100AgeNmprCnt;

    @Column(name = "male_nmpr_cnt")
    private Integer maleNmprCnt;

    @Column(name = "feml_nmpr_cnt")
    private Integer femlNmprCnt;

    @Column(name = "tot_nmpr_cnt")
    private Integer totNmprCnt;

    @Column(name = "stats_ym")
    private String statsYm;

    public Population(String yyyyMm, Integer regSeCd, String ctpvNm, String dongNm, String tong, String ban, String liNm,
                      Integer lv, String stdgCd, String stdgNm, String sggNm, String admmCd,
                      Integer male0AgeNmprCnt, Integer feml0AgeNmprCnt, Integer male10AgeNmprCnt,Integer feml10AgeNmprCnt,
                      Integer male20AgeNmprCnt, Integer feml20AgeNmprCnt, Integer male30AgeNmprCnt, Integer feml30AgeNmprCnt,
                      Integer male40AgeNmprCnt, Integer feml40AgeNmprCnt, Integer male50AgeNmprCnt, Integer feml50AgeNmprCnt,
                      Integer male60AgeNmprCnt, Integer feml60AgeNmprCnt, Integer male70AgeNmprCnt, Integer feml70AgeNmprCnt,
                      Integer male80AgeNmprCnt, Integer feml80AgeNmprCnt, Integer male90AgeNmprCnt, Integer feml90AgeNmprCnt,
                      Integer male100AgeNmprCnt, Integer feml100AgeNmprCnt, Integer maleNmprCnt, Integer femlNmprCnt,
                      Integer totNmprCnt, String statsYm) {
        this.yyyyMm = yyyyMm;
        this.regSeCd = regSeCd;
        this.ctpvNm = ctpvNm;
        this.dongNm = dongNm;
        this.tong = tong;
        this.ban = ban;
        this.liNm = liNm;
        this.lv = lv;
        this.stdgCd = stdgCd;
        this.stdgNm = stdgNm;
        this.sggNm = sggNm;
        this.admmCd = admmCd;
        this.male0AgeNmprCnt = male0AgeNmprCnt;
        this.feml0AgeNmprCnt = feml0AgeNmprCnt;
        this.male10AgeNmprCnt = male10AgeNmprCnt;
        this.feml10AgeNmprCnt = feml10AgeNmprCnt;
        this.male20AgeNmprCnt = male20AgeNmprCnt;
        this.feml20AgeNmprCnt = feml20AgeNmprCnt;
        this.male30AgeNmprCnt = male30AgeNmprCnt;
        this.feml30AgeNmprCnt = feml30AgeNmprCnt;
        this.male40AgeNmprCnt = male40AgeNmprCnt;
        this.feml40AgeNmprCnt = feml40AgeNmprCnt;
        this.male50AgeNmprCnt = male50AgeNmprCnt;
        this.feml50AgeNmprCnt = feml50AgeNmprCnt;
        this.male60AgeNmprCnt = male60AgeNmprCnt;
        this.feml60AgeNmprCnt = feml60AgeNmprCnt;
        this.male70AgeNmprCnt = male70AgeNmprCnt;
        this.feml70AgeNmprCnt = feml70AgeNmprCnt;
        this.male80AgeNmprCnt = male80AgeNmprCnt;
        this.feml80AgeNmprCnt = feml80AgeNmprCnt;
        this.male90AgeNmprCnt = male90AgeNmprCnt;
        this.feml90AgeNmprCnt = feml90AgeNmprCnt;
        this.male100AgeNmprCnt = male100AgeNmprCnt;
        this.feml100AgeNmprCnt = feml100AgeNmprCnt;
        this.maleNmprCnt = maleNmprCnt;
        this.femlNmprCnt = femlNmprCnt;
        this.totNmprCnt = totNmprCnt;
        this.statsYm = statsYm;
    }

}
