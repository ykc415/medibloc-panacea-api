package org.medibloc.panacea.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.medibloc.panacea.domain.did.PubKey;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgDeleteService implements PanaceaTransactionMessage {
    private String type = "did/MsgCreateDID";
    private Value value;

    @Getter @Setter @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPropertyOrder(alphabetic = true)
    public static class Value {
        private String did;
        @JsonProperty("owner_address")
        private String ownerAddress;
        @JsonProperty("service_id")
        private String serviceId;
    }
}
