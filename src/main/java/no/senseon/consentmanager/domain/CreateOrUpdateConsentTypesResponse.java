package no.senseon.consentmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import no.senseon.consentmanager.entities.ConsentTypesHistory;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateConsentTypesResponse {
    private ConsentTypesChannelIdentifier consentTypesChannelIdentifier;
    private ConsentTypesDetail consentTypesDetail;
    private List<ConsentTypesHistory> consentTypesHistories;
    @JsonIgnore
    private boolean newConsentTypesIndicator;
}
