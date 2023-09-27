package jhproject.datascraper.population.scraper;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "population", url = "https://apis.data.go.kr/1741000/stdgSexdAgePpltn/selectStdgSexdAgePpltn")
public interface PublicDataPopulationClient {

    @GetMapping
    public String getPopulation(@SpringQueryMap PublicDataPopulationGetParameter parameter);

}
