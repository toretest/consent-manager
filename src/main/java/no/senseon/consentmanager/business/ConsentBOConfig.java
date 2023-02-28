package no.senseon.consentmanager.business;

import lombok.Builder;
import lombok.Getter;
import no.senseon.consentmanager.repositories.*;

@Builder
@Getter
public class ConsentBOConfig {
    private final ConsentTypesRepository consentTypesRepository;
    private final ConsentRepository consentRepository;
    private final ConsentCustomerRepository consentCustomerRepository;
    private final ConsentHistoryRepository consentHistoryRepository;
    private final ConsentChannelRepository consentChannelRepository;
}
