package no.senseon.consentmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ConsentTypesResponse {
    private List<ConsentType> consentTypes;
}
