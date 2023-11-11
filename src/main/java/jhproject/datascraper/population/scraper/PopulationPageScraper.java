package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.PopulationScrapData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PopulationPageScraper {

    @Value("${jhproject.public-data.service-key}")
    public String serviceKey;

    private final PublicDataPopulationClient publicDataPopulationClient;

    public List<PopulationScrapData> scrap(PopulationScrapParameter parameter) {

        PublicDataPopulationGetParameter getParameter = parameter.toPublicDataPopulationGetParameter(serviceKey);
        PublicDataPopulationGetResponse response = publicDataPopulationClient.getPopulation(getParameter);
        List<PopulationScrapData> result = convert(parameter, response);

        if(response.hasNextPage()) {
            List<PopulationScrapData> nextPageResult = scrap(parameter.nextPage());
            result = Stream.concat(result.stream(), nextPageResult.stream()).toList();
        }

        return result;
    }


    private List<PopulationScrapData> convert(PopulationScrapParameter parameter, PublicDataPopulationGetResponse response) {
        if (Objects.isNull(response) || CollectionUtils.isEmpty(response.getResult())) {
            return List.of();
        }

        return  response.getResult().stream().map(r -> new PopulationScrapData(parameter, r)).toList();
    }


}
