package jhproject.datascraper.population.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "population_scrap_log")
public class PopulationScrapLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "yyyy")
    private Integer yyyy;

    @Column(name = "mm")
    private Integer mm;

    @Column(name = "scrap_start_dtm")
    private LocalDateTime scrapStartDtm;

    @Column(name = "scrap_end_dtm")
    private LocalDateTime scrapEndDtm;
}
