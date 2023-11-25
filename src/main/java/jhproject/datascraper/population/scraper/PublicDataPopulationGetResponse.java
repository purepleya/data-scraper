package jhproject.datascraper.population.scraper;

import com.fasterxml.jackson.annotation.JsonFormat;
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

        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
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
            this.male0AgeNmprCnt = Objects.isNull(male0AgeNmprCnt) ? "0" : male0AgeNmprCnt.toString();
            this.feml0AgeNmprCnt = Objects.isNull(feml0AgeNmprCnt) ? "0" : feml0AgeNmprCnt.toString();
            this.male10AgeNmprCnt = Objects.isNull(male10AgeNmprCnt) ? "0" : male10AgeNmprCnt.toString();
            this.feml10AgeNmprCnt = Objects.isNull(feml10AgeNmprCnt) ? "0" : feml10AgeNmprCnt.toString();
            this.male20AgeNmprCnt = Objects.isNull(male20AgeNmprCnt) ? "0" : male20AgeNmprCnt.toString();
            this.feml20AgeNmprCnt = Objects.isNull(feml20AgeNmprCnt) ? "0" : feml20AgeNmprCnt.toString();
            this.male30AgeNmprCnt = Objects.isNull(male30AgeNmprCnt) ? "0" : male30AgeNmprCnt.toString();
            this.feml30AgeNmprCnt = Objects.isNull(feml30AgeNmprCnt) ? "0" : feml30AgeNmprCnt.toString();
            this.male40AgeNmprCnt = Objects.isNull(male40AgeNmprCnt) ? "0" : male40AgeNmprCnt.toString();
            this.feml40AgeNmprCnt = Objects.isNull(feml40AgeNmprCnt) ? "0" : feml40AgeNmprCnt.toString();
            this.male50AgeNmprCnt = Objects.isNull(male50AgeNmprCnt) ? "0" : male50AgeNmprCnt.toString();
            this.feml50AgeNmprCnt = Objects.isNull(feml50AgeNmprCnt) ? "0" : feml50AgeNmprCnt.toString();
            this.male60AgeNmprCnt = Objects.isNull(male60AgeNmprCnt) ? "0" : male60AgeNmprCnt.toString();
            this.feml60AgeNmprCnt = Objects.isNull(feml60AgeNmprCnt) ? "0" : feml60AgeNmprCnt.toString();
            this.male70AgeNmprCnt = Objects.isNull(male70AgeNmprCnt) ? "0" : male70AgeNmprCnt.toString();
            this.feml70AgeNmprCnt = Objects.isNull(feml70AgeNmprCnt) ? "0" : feml70AgeNmprCnt.toString();
            this.male80AgeNmprCnt = Objects.isNull(male80AgeNmprCnt) ? "0" : male80AgeNmprCnt.toString();
            this.feml80AgeNmprCnt = Objects.isNull(feml80AgeNmprCnt) ? "0" : feml80AgeNmprCnt.toString();
            this.male90AgeNmprCnt = Objects.isNull(male90AgeNmprCnt) ? "0" : male90AgeNmprCnt.toString();
            this.feml90AgeNmprCnt = Objects.isNull(feml90AgeNmprCnt) ? "0" : feml90AgeNmprCnt.toString();
            this.male100AgeNmprCnt = Objects.isNull(male100AgeNmprCnt) ? "0" : male100AgeNmprCnt.toString();
            this.feml100AgeNmprCnt = Objects.isNull(feml100AgeNmprCnt) ? "0" : feml100AgeNmprCnt.toString();
            this.maleNmprCnt = Objects.isNull(maleNmprCnt) ? "0" : maleNmprCnt.toString();
            this.femlNmprCnt = Objects.isNull(femlNmprCnt) ? "0" : femlNmprCnt.toString();
            this.totNmprCnt = Objects.isNull(totNmprCnt) ? "0" : totNmprCnt.toString();
            this.statsYm = statsYm;
        }
    }
}
