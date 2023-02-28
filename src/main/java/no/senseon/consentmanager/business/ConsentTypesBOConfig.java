package no.senseon.consentmanager.business;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import no.senseon.consentmanager.repositories.ConsentChannelRepository;
import no.senseon.consentmanager.repositories.ConsentTypesHistoryRepository;
import no.senseon.consentmanager.repositories.ConsentTypesRepository;

@Builder
@Getter
public class ConsentTypesBOConfig {
    @NotNull
    private final ConsentChannelRepository consentChannelRepository;
    @NotNull
    private final ConsentTypesRepository consentTypesRepository;
    @NotNull
    private final ConsentTypesHistoryRepository consentTypesHistoryRepository;
}
