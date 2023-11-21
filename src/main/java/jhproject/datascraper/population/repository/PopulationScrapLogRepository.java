package jhproject.datascraper.population.repository;

import jhproject.datascraper.population.entity.PopulationScrapLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PopulationScrapLogRepository extends JpaRepository<PopulationScrapLog, Long> {

}
