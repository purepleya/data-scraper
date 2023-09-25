package jhproject.datascraper.population.scraper;

import jhproject.datascraper.population.Population;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public class PopulationScraper {

    public List<Population> scrap(PopulationScrapYearMonth yearMonth) {
//        최초 파라미터로 api 호출해서 나온 결과로 다음 파라미터 생성
//        그다음 또 API 호출
//        for (광역 시도 단위 코드)
//          for (법정동 코드)
//              for (등록 구분)
//        TODO 위 루프를 어떻게 구현 할 것인가? 어떻게 테스트 할 것인가?
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
