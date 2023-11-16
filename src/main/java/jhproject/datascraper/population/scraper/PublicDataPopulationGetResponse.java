package jhproject.datascraper.population.scraper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class PublicDataPopulationGetResponse {

    @JsonProperty("Response")
    private Response response;

    public PublicDataPopulationGetResponse() {
    }

    public PublicDataPopulationGetResponse(Head head, Items items) {
        this.response = new Response(head, items);
    }

    public List<Item> getResult() {
        if (Objects.isNull(response) || Objects.isNull(response.items) || Objects.isNull(response.items.item)) {
            return List.of();
        }

        return response.items.item;
    }

    public boolean hasNextPage() {
        if (Objects.isNull(response) || Objects.isNull(response.head)) {
            return false;
        }
        return Integer.parseInt(response.head.pageNo) * Integer.parseInt(response.head.numOfRows) < Integer.parseInt(response.head.totalCount);
    }


    @Getter
    @Setter
    public static class Response {
        private Head head;
        private Items items;

        public Response() {
        }
        public Response(Head head, Items items) {
            this.head = head;
            this.items = items;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Items {
        private List<Item> item;

        public Items(List<Item> item) {
            this.item = item;
        }
    }


    @Setter
    @Getter
    @NoArgsConstructor
    public static class Head {
        private String pageNo;
        private String resultCode;
        private String totalCount;
        private String numOfRows;
        private String resultMsg;

        public Head(Integer pageNo, String resultCode, Integer totalCount, Integer numOfRows, String resultMsg) {
            this.pageNo = String.valueOf(pageNo);
            this.resultCode = resultCode;
            this.totalCount = String.valueOf(totalCount);
            this.numOfRows = String.valueOf(numOfRows);
            this.resultMsg = resultMsg;
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Item {
        private String ctpvNm;
        private String dongNm;
        private String tong;
        private String ban;
        private String liNm;
        private String stdgCd;
        private String stdgNm;
        private String sggNm;
        private String admmCd;
        private String male0AgeNmprCnt;
        private String feml0AgeNmprCnt;
        private String male10AgeNmprCnt;
        private String feml10AgeNmprCnt;
        private String male20AgeNmprCnt;
        private String feml20AgeNmprCnt;
        private String male30AgeNmprCnt;
        private String feml30AgeNmprCnt;
        private String male40AgeNmprCnt;
        private String feml40AgeNmprCnt;
        private String male50AgeNmprCnt;
        private String feml50AgeNmprCnt;
        private String male60AgeNmprCnt;
        private String feml60AgeNmprCnt;
        private String male70AgeNmprCnt;
        private String feml70AgeNmprCnt;
        private String male80AgeNmprCnt;
        private String feml80AgeNmprCnt;
        private String male90AgeNmprCnt;
        private String feml90AgeNmprCnt;
        private String male100AgeNmprCnt;
        private String feml100AgeNmprCnt;
        private String maleNmprCnt;
        private String femlNmprCnt;
        private String totNmprCnt;
        private String statsYm;

        public Item(String ctpvNm, String dongNm, String tong, String ban, String liNm, String stdgCd, String stdgNm, String sggNm, String admmCd, Integer male0AgeNmprCnt, Integer feml0AgeNmprCnt, Integer male10AgeNmprCnt, Integer feml10AgeNmprCnt, Integer male20AgeNmprCnt, Integer feml20AgeNmprCnt, Integer male30AgeNmprCnt, Integer feml30AgeNmprCnt, Integer male40AgeNmprCnt, Integer feml40AgeNmprCnt, Integer male50AgeNmprCnt, Integer feml50AgeNmprCnt, Integer male60AgeNmprCnt, Integer feml60AgeNmprCnt, Integer male70AgeNmprCnt, Integer feml70AgeNmprCnt, Integer male80AgeNmprCnt, Integer feml80AgeNmprCnt, Integer male90AgeNmprCnt, Integer feml90AgeNmprCnt, Integer male100AgeNmprCnt, Integer feml100AgeNmprCnt, Integer maleNmprCnt, Integer femlNmprCnt, Integer totNmprCnt, String statsYm) {
            this.ctpvNm = ctpvNm;
            this.dongNm = dongNm;
            this.tong = tong;
            this.ban = ban;
            this.liNm = liNm;
            this.stdgCd = stdgCd;
            this.stdgNm = stdgNm;
            this.sggNm = sggNm;
            this.admmCd = admmCd;
            this.male0AgeNmprCnt = String.valueOf(male0AgeNmprCnt);
            this.feml0AgeNmprCnt = String.valueOf(feml0AgeNmprCnt);
            this.male10AgeNmprCnt = String.valueOf(male10AgeNmprCnt);
            this.feml10AgeNmprCnt = String.valueOf(feml10AgeNmprCnt);
            this.male20AgeNmprCnt = String.valueOf(male20AgeNmprCnt);
            this.feml20AgeNmprCnt = String.valueOf(feml20AgeNmprCnt);
            this.male30AgeNmprCnt = String.valueOf(male30AgeNmprCnt);
            this.feml30AgeNmprCnt = String.valueOf(feml30AgeNmprCnt);
            this.male40AgeNmprCnt = String.valueOf(male40AgeNmprCnt);
            this.feml40AgeNmprCnt = String.valueOf(feml40AgeNmprCnt);
            this.male50AgeNmprCnt = String.valueOf(male50AgeNmprCnt);
            this.feml50AgeNmprCnt = String.valueOf(feml50AgeNmprCnt);
            this.male60AgeNmprCnt = String.valueOf(male60AgeNmprCnt);
            this.feml60AgeNmprCnt = String.valueOf(feml60AgeNmprCnt);
            this.male70AgeNmprCnt = String.valueOf(male70AgeNmprCnt);
            this.feml70AgeNmprCnt = String.valueOf(feml70AgeNmprCnt);
            this.male80AgeNmprCnt = String.valueOf(male80AgeNmprCnt);
            this.feml80AgeNmprCnt = String.valueOf(feml80AgeNmprCnt);
            this.male90AgeNmprCnt = String.valueOf(male90AgeNmprCnt);
            this.feml90AgeNmprCnt = String.valueOf(feml90AgeNmprCnt);
            this.male100AgeNmprCnt = String.valueOf(male100AgeNmprCnt);
            this.feml100AgeNmprCnt = String.valueOf(feml100AgeNmprCnt);
            this.maleNmprCnt = String.valueOf(maleNmprCnt);
            this.femlNmprCnt = String.valueOf(femlNmprCnt);
            this.totNmprCnt = String.valueOf(totNmprCnt);
            this.statsYm = statsYm;
        }
    }
}
