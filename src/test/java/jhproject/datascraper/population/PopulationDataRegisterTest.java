package jhproject.datascraper.population;

import jakarta.persistence.Column;
import jhproject.datascraper.TestContainerConfiguration;
import jhproject.datascraper.population.entity.Population;
import jhproject.datascraper.population.repository.PopulationRepository;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PublicDataPopulationGetResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Import(TestContainerConfiguration.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto = none")
class PopulationDataRegisterTest {

    @Autowired
    private PopulationDataRegister populationDataRegister;

    @Autowired
    private PopulationRepository populationRepository;

    @Test
    @Sql("/sql/population/repository/CREATE_population.sql")
    @DisplayName("List<PopulationScrapData>를 파라미터를 Population 엔티티로 변환하여 저장한다.")
    void save() {
        PopulationScrapParameter parameter1 = new PopulationScrapParameter("202210", "1", 1, 1, 1);
        PopulationScrapParameter parameter2 = new PopulationScrapParameter("202212", "2", 2, 2, 2);

        PublicDataPopulationGetResponse.Item responseItem1 = new PublicDataPopulationGetResponse.Item(
                "ctpvNm", "dongNm", "tong", "ban", "liNm",
                "stdgCd", "stdgNm", "sggNm", "1",
                0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11,
                12, 13, 14, 15,
                16, 17, 18, 19,
                20, 21, 22, 23,
                24, "statsYm"
        );
        PublicDataPopulationGetResponse.Item responseItem2 = new PublicDataPopulationGetResponse.Item(
                "ctpvNm", "dongNm", "tong", "ban", "liNm",
                "stdgCd", "stdgNm", "sggNm", "1",
                10, 11, 12, 13,
                14, 15, 16, 17,
                18, 19, 20, 21,
                22, 23, 24, 25,
                26, 27, 28, 29,
                30, 31, 32, 33,
                34, "statsYm"
        );

        List<PopulationScrapData> scrapDataList = List.of(
                new PopulationScrapData(parameter1, responseItem1),
                new PopulationScrapData(parameter2, responseItem2)
        );

        populationDataRegister.save(scrapDataList);

        List<Population> entities = populationRepository.findAll();

        assertEquals(2, entities.size());
        assertEquals("202210", entities.get(0).getYyyyMm());
        assertEquals("1", entities.get(0).getRegSeCd());
        assertEquals("ctpvNm", entities.get(0).getCtpvNm());
        assertEquals("dongNm", entities.get(0).getDongNm());
        assertEquals("tong", entities.get(0).getTong());
        assertEquals("ban", entities.get(0).getBan());
        assertEquals("liNm", entities.get(0).getLiNm());
        assertEquals("stdgCd", entities.get(0).getStdgCd());
        assertEquals("stdgNm", entities.get(0).getStdgNm());
        assertEquals("sggNm", entities.get(0).getSggNm());
        assertEquals("1", entities.get(0).getAdmmCd());
        assertEquals(Integer.valueOf(0), entities.get(0).getMale0AgeNmprCnt());
        assertEquals(Integer.valueOf(1), entities.get(0).getFeml0AgeNmprCnt());
        assertEquals(Integer.valueOf(2), entities.get(0).getMale10AgeNmprCnt());
        assertEquals(Integer.valueOf(3), entities.get(0).getFeml10AgeNmprCnt());
        assertEquals(Integer.valueOf(4), entities.get(0).getMale20AgeNmprCnt());
        assertEquals(Integer.valueOf(5), entities.get(0).getFeml20AgeNmprCnt());
        assertEquals(Integer.valueOf(6), entities.get(0).getMale30AgeNmprCnt());
        assertEquals(Integer.valueOf(7), entities.get(0).getFeml30AgeNmprCnt());
        assertEquals(Integer.valueOf(8), entities.get(0).getMale40AgeNmprCnt());
        assertEquals(Integer.valueOf(9), entities.get(0).getFeml40AgeNmprCnt());
        assertEquals(Integer.valueOf(10), entities.get(0).getMale50AgeNmprCnt());
        assertEquals(Integer.valueOf(11), entities.get(0).getFeml50AgeNmprCnt());
        assertEquals(Integer.valueOf(12), entities.get(0).getMale60AgeNmprCnt());
        assertEquals(Integer.valueOf(13), entities.get(0).getFeml60AgeNmprCnt());
        assertEquals(Integer.valueOf(14), entities.get(0).getMale70AgeNmprCnt());
        assertEquals(Integer.valueOf(15), entities.get(0).getFeml70AgeNmprCnt());
        assertEquals(Integer.valueOf(16), entities.get(0).getMale80AgeNmprCnt());
        assertEquals(Integer.valueOf(17), entities.get(0).getFeml80AgeNmprCnt());
        assertEquals(Integer.valueOf(18), entities.get(0).getMale90AgeNmprCnt());
        assertEquals(Integer.valueOf(19), entities.get(0).getFeml90AgeNmprCnt());
        assertEquals(Integer.valueOf(20), entities.get(0).getMale100AgeNmprCnt());
        assertEquals(Integer.valueOf(21), entities.get(0).getFeml100AgeNmprCnt());
        assertEquals(Integer.valueOf(22), entities.get(0).getMaleNmprCnt());
        assertEquals(Integer.valueOf(23), entities.get(0).getFemlNmprCnt());
        assertEquals(Integer.valueOf(24), entities.get(0).getTotNmprCnt());
        assertEquals("statsYm", entities.get(0).getStatsYm());

        assertEquals("202212", entities.get(1).getYyyyMm());
        assertEquals("2", entities.get(1).getRegSeCd());
        assertEquals("ctpvNm", entities.get(1).getCtpvNm());
        assertEquals("dongNm", entities.get(1).getDongNm());
        assertEquals("tong", entities.get(1).getTong());
        assertEquals("ban", entities.get(1).getBan());
        assertEquals("liNm", entities.get(1).getLiNm());
        assertEquals("stdgCd", entities.get(1).getStdgCd());
        assertEquals("stdgNm", entities.get(1).getStdgNm());
        assertEquals("sggNm", entities.get(1).getSggNm());
        assertEquals("1", entities.get(1).getAdmmCd());
        assertEquals(Integer.valueOf(10), entities.get(1).getMale0AgeNmprCnt());
        assertEquals(Integer.valueOf(11), entities.get(1).getFeml0AgeNmprCnt());
        assertEquals(Integer.valueOf(12), entities.get(1).getMale10AgeNmprCnt());
        assertEquals(Integer.valueOf(13), entities.get(1).getFeml10AgeNmprCnt());
        assertEquals(Integer.valueOf(14), entities.get(1).getMale20AgeNmprCnt());
        assertEquals(Integer.valueOf(15), entities.get(1).getFeml20AgeNmprCnt());
        assertEquals(Integer.valueOf(16), entities.get(1).getMale30AgeNmprCnt());
        assertEquals(Integer.valueOf(17), entities.get(1).getFeml30AgeNmprCnt());
        assertEquals(Integer.valueOf(18), entities.get(1).getMale40AgeNmprCnt());
        assertEquals(Integer.valueOf(19), entities.get(1).getFeml40AgeNmprCnt());
        assertEquals(Integer.valueOf(20), entities.get(1).getMale50AgeNmprCnt());
        assertEquals(Integer.valueOf(21), entities.get(1).getFeml50AgeNmprCnt());
        assertEquals(Integer.valueOf(22), entities.get(1).getMale60AgeNmprCnt());
        assertEquals(Integer.valueOf(23), entities.get(1).getFeml60AgeNmprCnt());
        assertEquals(Integer.valueOf(24), entities.get(1).getMale70AgeNmprCnt());
        assertEquals(Integer.valueOf(25), entities.get(1).getFeml70AgeNmprCnt());
        assertEquals(Integer.valueOf(26), entities.get(1).getMale80AgeNmprCnt());
        assertEquals(Integer.valueOf(27), entities.get(1).getFeml80AgeNmprCnt());
        assertEquals(Integer.valueOf(28), entities.get(1).getMale90AgeNmprCnt());
        assertEquals(Integer.valueOf(29), entities.get(1).getFeml90AgeNmprCnt());
        assertEquals(Integer.valueOf(30), entities.get(1).getMale100AgeNmprCnt());
        assertEquals(Integer.valueOf(31), entities.get(1).getFeml100AgeNmprCnt());
        assertEquals(Integer.valueOf(32), entities.get(1).getMaleNmprCnt());
        assertEquals(Integer.valueOf(33), entities.get(1).getFemlNmprCnt());
        assertEquals(Integer.valueOf(34), entities.get(1).getTotNmprCnt());
        assertEquals("statsYm", entities.get(1).getStatsYm());
    }


}