package jhproject.datascraper.population.repository;

import jakarta.persistence.EntityManager;
import jhproject.datascraper.population.entity.PopulationScrapLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PopulationScrapLogRepository  {

    private final EntityManager entityManager;

    public Optional<PopulationScrapLog> getLastLog() {
        String query = "SELECT p FROM PopulationScrapLog p ORDER BY p.scrapEndDtm DESC";
        return entityManager.createQuery(query, PopulationScrapLog.class)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }


    @Transactional
    public List<PopulationScrapLog> saveAllAndFlush(Iterable<PopulationScrapLog> entities) {
        JpaRepository<PopulationScrapLog, Long> defaultRepository = new SimpleJpaRepository<>(PopulationScrapLog.class, entityManager);
        return defaultRepository.saveAllAndFlush(entities);
    }

}
