package jhproject.datascraper.population.scraper;

import lombok.Getter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;
import java.util.Objects;

public class PublicDataPopulationGetResponse {

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
        return response.head.pageNo * response.head.numOfRows < response.head.totalCount;
    }


    public class Response {
        private Head head;
        private Items items;

        public Response() {
        }
        public Response(Head head, Items items) {
            this.head = head;
            this.items = items;
        }
    }

    public record Items(
        List<Item> item
    ) {
    }


    public record Head(
            Integer pageNo,
            String resultCode,
            Integer totalCount,
            Integer numOfRows,
            String resultMsg
    ) {
    }


    public record Item(
            String ctpvNm,
            String dongNm,
            String tong,
            String ban,
            String liNm,
            String stdgCd,
            String stdgNm,
            String sggNm,
            String admmCd,
            Integer male0AgeNmprCnt,
            Integer feml0AgeNmprCnt,
            Integer male10AgeNmprCnt,
            Integer feml10AgeNmprCnt,
            Integer male20AgeNmprCnt,
            Integer feml20AgeNmprCnt,
            Integer male30AgeNmprCnt,
            Integer feml30AgeNmprCnt,
            Integer male40AgeNmprCnt,
            Integer feml40AgeNmprCnt,
            Integer male50AgeNmprCnt,
            Integer feml50AgeNmprCnt,
            Integer male60AgeNmprCnt,
            Integer feml60AgeNmprCnt,
            Integer male70AgeNmprCnt,
            Integer feml70AgeNmprCnt,
            Integer male80AgeNmprCnt,
            Integer feml80AgeNmprCnt,
            Integer male90AgeNmprCnt,
            Integer feml90AgeNmprCnt,
            Integer male100AgeNmprCnt,
            Integer feml100AgeNmprCnt,
            Integer maleNmprCnt,
            Integer femlNmprCnt,
            Integer totNmprCnt,
            String statsYm
    ) {
    }
}
