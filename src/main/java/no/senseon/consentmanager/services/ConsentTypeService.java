package no.senseon.consentmanager.services;

import no.senseon.consentmanager.domain.ConsentTypesResponse;
import no.senseon.consentmanager.domain.CreateOrUpdateConsentTypesRequest;
import no.senseon.consentmanager.domain.CreateOrUpdateConsentTypesResponse;

public interface ConsentTypeService {
    CreateOrUpdateConsentTypesResponse createOrUpdateConsentTypes(CreateOrUpdateConsentTypesRequest createOrUpdateConsentTypesRequest);
    ConsentTypesResponse getAllConsentTypes(String consentChannelName);
    ConsentTypesResponse getConsentTypesHistoryByConsentTypeNameAndChannelName(String consentTypeName, String channelName);
}
