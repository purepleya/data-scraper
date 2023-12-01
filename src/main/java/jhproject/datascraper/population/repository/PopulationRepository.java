package jhproject.datascraper.population.repository;

import jhproject.datascraper.population.entity.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population, Long> {

    @Query("select p from Population p where p.yearMonth = :yearMonth and p.regSeCd = :regSeCd and p.lv >= :lv")
    public List<Population> findByYearMonthAndRegSeCdAndGoeLv(@Param("yearMonth")String yearMonth, @Param("regSeCd")Integer regSeCd, @Param("lv")Integer lv);
}
