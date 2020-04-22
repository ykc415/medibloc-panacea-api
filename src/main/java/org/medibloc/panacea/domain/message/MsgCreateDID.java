package org.medibloc.panacea.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.medibloc.panacea.domain.did.PubKey;
import org.medibloc.panacea.domain.did.Service;

import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgCreateDID implements PanaceaTransactionMessage {
    private String type = "did/MsgCreateDID";
    private Value value;

    @Getter @Setter @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPropertyOrder(alphabetic = true)
    public static class Value {
        @JsonProperty("owner_address")
        private String ownerAddress;
        private List<Service> services;
        @JsonProperty("public_keys")
        private List<PubKey> pubKeys;
    }
}
