package pelican.co_labor.domain.hospital;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HospitalResponse {
    private Header header;
    private Body body;

    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        @JacksonXmlProperty(localName = "items")
        private Items items;

        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    @Setter
    public static class Items {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "item")
        private List<Item> itemList;
    }

    @Getter
    @Setter
    public static class Item {
        private String dutyAddr;
        private String dutyName;
        private String dutyTel1;
        private int rnum;
        private double wgs84Lat;
        private double wgs84Lon;
    }
}
