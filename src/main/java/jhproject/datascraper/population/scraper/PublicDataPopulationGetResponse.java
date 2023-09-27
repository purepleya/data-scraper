package jhproject.datascraper.population.scraper;

import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

public class PublicDataPopulationGetResponse {

    private Response response;

    public Response getResponse() {
        return this.response;
    }


    public class Response {
        private Head head;
        private Items items;
    }

    public class Items {
        private List<Item> item;
    }

    public class Head {

//              "pageNo": "1",
//              "resultCode": "0",
//              "totalCount": "17",
//              "numOfRows": "100",
//              "resultMsg": "NORMAL_SERVICE"
    }

    public class Item {
        "tong": "",
                "male50AgeNmprCnt": "732824",
                "feml40AgeNmprCnt": "724211",
                "male80AgeNmprCnt": "134220",
                "male20AgeNmprCnt": "655192",
                "stdgNm": "",
                "ban": "",
                "totNmprCnt": "9414115",
                "feml0AgeNmprCnt": "257451",
                "male40AgeNmprCnt": "709245",
                "ctpvNm": "서울특별시",
                "male90AgeNmprCnt": "11558",
                "dongNm": "",
                "feml10AgeNmprCnt": "362909",
                "feml50AgeNmprCnt": "757481",
                "male10AgeNmprCnt": "379700",
                "feml100AgeNmprCnt": "1165",
                "feml90AgeNmprCnt": "32311",
                "male30AgeNmprCnt": "713711",
                "feml20AgeNmprCnt": "720852",
                "feml60AgeNmprCnt": "691705",
                "femlNmprCnt": "4856495",
                "stdgCd": "1100000000",
                "liNm": "",
                "feml30AgeNmprCnt": "713153",
                "male0AgeNmprCnt": "271157",
                "feml80AgeNmprCnt": "198215",
                "male100AgeNmprCnt": "330",
                "maleNmprCnt": "4557620",
                "sggNm": "",
                "male60AgeNmprCnt": "620795",
                "male70AgeNmprCnt": "328888",
                "feml70AgeNmprCnt": "397042",
                "admmCd": "",
                "statsYm": "202306"
    }
}
