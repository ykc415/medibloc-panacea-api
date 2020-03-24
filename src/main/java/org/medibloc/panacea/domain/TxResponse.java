package org.medibloc.panacea.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class TxResponse {
    @JsonProperty("total_count")
    private String totalCount;
    private String count;
    @JsonProperty("page_number")
    private String pageNumber;
    @JsonProperty("page_total")
    private String pageTotal;
    private String limit;
    private List<Tx> txs;
}

@Getter @Setter @ToString
class Tx {
    private String height;
    private String txHash;
    @JsonProperty("raw_log")
    private String rawLog;
    private List<Log> logs;
    @JsonProperty("gas_wanted")
    private String gasWanted;
    @JsonProperty("gas_used")
    private String gasUsed;
    private StdTx tx;
    private String timestamp;
    private List<Event> events;
}

@Getter @Setter @ToString
class StdTx {
    private String type;
    private Value value;

    @Getter @Setter @ToString
    static class Value {
        private List<Msg> msg;
        private Fee fee;
        private List<Signature> signatures;
        private String memo;
    }
}

@Getter @Setter @ToString
class Signature {
    @JsonProperty("pub_key")
    private PubKey pubKey;
    private String signature;
}

@Getter @Setter @ToString
class Fee {
    private List<Coin> amount;
    private String gas;
}

@Getter @Setter @ToString
class Msg {
    private String type;
    private Object value;
}

@Getter @Setter @ToString
class Log {
    @JsonProperty("msg_index")
    private Long msgIndex;
    private boolean success;
    private String log;
    private List<Event> events;
}

@Getter @Setter @ToString
class Event {
    private String type;
    private List<Attribute> attributes;
}

@ToString @Getter @Setter
class Attribute {
    private String key;
    private String value;
}




