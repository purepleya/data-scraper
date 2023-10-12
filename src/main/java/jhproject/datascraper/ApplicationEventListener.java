package jhproject.datascraper;


import jhproject.datascraper.population.PopulationDataProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationEventListener {

    private final PopulationDataProcessor populationScrapProcessor;

//    @EventListener({ContextRefreshedEvent.class})
//    public void handleRefreshEvent() {
//        populationScrapProcessor.run();
//    }



}
