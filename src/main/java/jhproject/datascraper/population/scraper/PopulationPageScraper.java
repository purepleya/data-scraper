package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
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

    public List<Population> scrap(PopulationScrapParameter parameter) {

        PublicDataPopulationGetParameter getParameter = parameter.toPublicDataPopulationGetParameter(serviceKey);
        PublicDataPopulationGetResponse response = publicDataPopulationClient.getPopulation(getParameter);
        List<Population> result = convert(response);

        if(response.hasNextPage()) {
            List<Population> nextPageResult = scrap(parameter.nextPage());
            result = Stream.concat(result.stream(), nextPageResult.stream()).toList();
        }

        return result;
    }


    private List<Population> convert(PublicDataPopulationGetResponse response) {
        if (Objects.isNull(response) || CollectionUtils.isEmpty(response.getResult())) {
            return List.of();
        }

        return  response.getResult().stream().map(Population::new).toList();
    }


}
