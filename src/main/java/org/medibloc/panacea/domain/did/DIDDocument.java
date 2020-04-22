package org.medibloc.panacea.domain.did;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class DIDDocument {
    @JsonProperty("@context")
    private String context;
    private String id;
    private List<PubKeyWithId> publicKey;
    private List<String> authentication;
    private List<Service> service;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DIDDocument document = (DIDDocument) o;
        return Objects.equal(context, document.context) &&
                Objects.equal(id, document.id) &&
                Objects.equal(publicKey, document.publicKey) &&
                Objects.equal(authentication, document.authentication) &&
                Objects.equal(service, document.service);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(context, id, publicKey, authentication, service);
    }
}
