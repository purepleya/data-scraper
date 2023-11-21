package jhproject.datascraper.population.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "population_scrap_log")
public class PopulationScrapLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "yyyy_mm")
    private String yearMonth;

    @Column(name = "stdg_cd")
    private String stdgCd;

    @Column(name = "lv")
    private Integer lv;

    @Column(name = "reg_se_cd")
    private Integer regSeCd;

    @Column(name = "scrap_start_dtm")
    private LocalDateTime scrapStartDtm;

    @Column(name = "scrap_end_dtm")
    private LocalDateTime scrapEndDtm;

//    public PopulationScrapLog(Integer id, Integer yyyy, Integer mm, LocalDateTime scrapStartDtm, LocalDateTime scrapEndDtm) {
//        this.id = id;
//        this.yyyy = yyyy;
//        this.mm = mm;
//        this.scrapStartDtm = scrapStartDtm;
//        this.scrapEndDtm = scrapEndDtm;
//    }
//
//    public void setEndAt(LocalDateTime now) {
//        this.scrapEndDtm = now;
//    }
}
