package org.medibloc.panacea.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class MsgCreateBucket implements PanaceaTransactionMessage {
    private String type = "bucket/MsgCreateBucket";
    private Value value;

    @Getter @Setter @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPropertyOrder(alphabetic = true)
    public static class Value {
        private String bucket_name;
        private String bucket_owner;
        private String description;
    }
}
