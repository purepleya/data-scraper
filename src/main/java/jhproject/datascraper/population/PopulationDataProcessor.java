package jhproject.datascraper.population;

import jhproject.datascraper.population.scraper.PopulationPageScraper;
import jhproject.datascraper.population.scraper.PopulationScrapParameter;
import jhproject.datascraper.population.scraper.PopulationScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PopulationDataProcessor {

    private final PopulationScrapParameterGenerator populationScrapParameterGenerator;
    private final PopulationScrapLoggerBuilder loggerBuilder;
    private final PopulationPageScraper populationPageScraper;
    private final PopulationDataRegister populationDataRegister;


    public void run() throws InterruptedException {
//        TODO 데이터가 너무 많아서 처리 방식 변경 필요
//        연단위로 처리 하지말고 Scraper는 그냥 파라미터로 받은것을 처리하고 파라미터는 파라미터 제네레이터가 알아서 생성하도록 수정

        Optional<PopulationScrapParameter> parameterOptional;

        while ((parameterOptional = populationScrapParameterGenerator.generate()).isPresent()) {
            PopulationScrapParameter parameter = parameterOptional.get();
            PopulationScrapLoggerBuilder.PopulationScrapLogger logger = loggerBuilder.build(parameter.getYearMonth());
            logger.start();

            List<PopulationScrapData> populationScrapData = populationPageScraper.scrap(parameter);
            populationDataRegister.save(populationScrapData);

            logger.end();
        }
    }



}
