package no.senseon.consentmanager.domain;

import lombok.Builder;
import lombok.Getter;
import no.senseon.consentmanager.entities.ConsentTypesHistory;

import java.util.List;

@Builder
@Getter
public class ConsentType {
    private ConsentTypesChannelIdentifier consentTypesChannelIdentifier;
    private ConsentTypesDetail consentTypesDetail;
    private List<ConsentTypesHistory> consentTypesHistories;
}
