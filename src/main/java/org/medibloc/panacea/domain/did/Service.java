package org.medibloc.panacea.domain.did;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class Service {
    private String id;
    private String type;
    private String serviceEndPoint;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equal(id, service.id) &&
                Objects.equal(type, service.type) &&
                Objects.equal(serviceEndPoint, service.serviceEndPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, type, serviceEndPoint);
    }
}
