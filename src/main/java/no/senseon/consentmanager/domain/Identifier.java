package no.senseon.consentmanager.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import no.senseon.consentmanager.entities.IdentifierType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Identifier {
    @NotNull(message = "identifier.identifier is null in the request")
    private String identifier;
    @NotNull(message = "identifier.identifierType is null in the request")
    private IdentifierType identifierType;
    @NotNull(message = "identifier.consentTypesChannel is null in the request")
    private String consentTypesChannel;
}
