package no.senseon.consentmanager.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateConsentTypesRequest {
    @NotNull(message = "must always be given, ssn or email")
    private ConsentTypesChannelIdentifier consentTypesChannelIdentifier;
    @NotNull(message = "must alwaus be given")
    private ConsentTypesDetail consentTypesDetail;
}
