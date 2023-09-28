package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulationScraper {

    private final PublicDataPopulationClient publicDataPopulationClient;
    private final PublicDataPopulationGetParameterBuilder parameterBuilder;

    public List<Population> scrap(PopulationScrapYearMonth yearMonth) {
        PublicDataPopulationGetParameter parameter = parameterBuilder.build(yearMonth);
        List<Population> populations = scrap(parameter);



        while(parameter.hasNextRegSeCd()) {
            parameter = parameter.nextRegSeCd();
            populations.addAll(scrap(parameter));
        }

        return List.of();
    }

    private List<Population> scrapByLv(PublicDataPopulationGetParameter parameter) {
//        TODO 광역시도 단위 데이터 수집
        if (parameter.hasNextLv()) {
            List<PublicDataPopulationGetParameter> nextLvParameters = populations.stream()
                    .map(p -> parameterBuilder.build(parameter.nextLv(), p))
                    .toList();
        }
//    TODO response 모아서 Population Collection으로 변환
        return List.of();
    }

    private List<Population> scrapByRegSeCd(PublicDataPopulationGetParameter parameter) {
//        TODO 등록 구분 단위 데이터 수집

//    TODO response 모아서 Population Collection으로 변환
        return List.of();
    }


//    페이지를 증가 시키며 전체 페이지수집
    private List<Population> scrapByPage(PublicDataPopulationGetParameter parameter) {
        PublicDataPopulationGetResponse response = publicDataPopulationClient.getPopulation(parameter);

        while(response.hasNextPage()) {
            PublicDataPopulationGetResponse nextPageResponse = publicDataPopulationClient.getPopulation(parameter.nextPage());
        }

//    TODO response 모아서 Population Collection으로 변환
        return List.of();
    }

//        공공데이터 API 호출해서 데이터 수집
//        조회결과구분. 광역시도 단위 : 1, 시군구 단위 : 2, 읍면동 단위 : 3, 읍면동 통반 단위 : 4(기본값 : 4)
//        등록구분. 전체:1, 거주자:2, 거주불명자:3, 재외국민:4(기본값 : 1)
//        법정동코드

//    		?serviceKey=...
//            &type=JSON
//		&numOfRows=100
//            &srchFrYm=202306
//            &srchToYm=202306
//            &stdgCd=1000000000
//            &lv=1
//            &regSeCd=1
//            &pageNo=1


    private final int[] lv = {1, 2, 3, 4};

    private final int[] regSeCd = {1, 2, 3, 4};

    private record PopulationScrapParameters (
            String yearMonth,
            String stdgCd,
            int lv,
            int regSeCd,
            int pageNo
    ) {
        public static PopulationScrapParameters first(@NonNull PopulationScrapYearMonth yearMonth) {
            return new PopulationScrapParameters(
                    yearMonth.getYearMonth(),
                    "1000000000",
                    1,
                    1,
                    1
            );
        }
    }
}
