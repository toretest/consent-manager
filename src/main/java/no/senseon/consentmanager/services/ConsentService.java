package no.senseon.consentmanager.services;

import no.senseon.consentmanager.domain.ConsentDetails;
import no.senseon.consentmanager.domain.ConsentsResponse;
import no.senseon.consentmanager.domain.CreateOrdUpdateConsentRequest;
import no.senseon.consentmanager.domain.Identifier;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;

public interface ConsentService {
    ConsentsResponse createOrUpdate(CreateOrdUpdateConsentRequest createOrdUpdateConsentRequest, SecurityUserInfo securityUserInfo);

    ConsentsResponse getConsentsByCustomer(Identifier identifier, SecurityUserInfo securityUserInfo);

    ConsentDetails getConsentDetailsByConsentName(String consentName);

    ConsentsResponse getConsentHistoryByCustomer(Identifier identifier, SecurityUserInfo securityUserInfo);
}
