package no.senseon.consentmanager.services;

import no.senseon.consentmanager.business.ConsentBO;
import no.senseon.consentmanager.business.ConsentBOConfig;
import no.senseon.consentmanager.domain.ConsentDetails;
import no.senseon.consentmanager.domain.ConsentsResponse;
import no.senseon.consentmanager.domain.CreateOrdUpdateConsentRequest;
import no.senseon.consentmanager.domain.Identifier;
import no.senseon.consentmanager.repositories.*;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ConsentServiceImpl implements ConsentService {
    private final ConsentRepository consentRepository;
    private final ConsentTypesRepository consentTypesRepository;
    private final ConsentCustomerRepository consentCustomerRepository;
    private final ConsentHistoryRepository consentHistoryRepository;
    private final ConsentChannelRepository consentChannelRepository;

    @Inject
    public ConsentServiceImpl(ConsentRepository consentRepository,
                              ConsentTypesRepository consentTypesRepository,
                              ConsentCustomerRepository consentCustomerRepository,
                              ConsentHistoryRepository consentHistoryRepository,
                              ConsentChannelRepository consentChannelRepository
    ) {
        this.consentTypesRepository = consentTypesRepository;
        this.consentRepository = consentRepository;
        this.consentCustomerRepository = consentCustomerRepository;
        this.consentHistoryRepository = consentHistoryRepository;
        this.consentChannelRepository = consentChannelRepository;
    }

    @Override
    public ConsentsResponse createOrUpdate(CreateOrdUpdateConsentRequest createOrdUpdateConsentRequest, SecurityUserInfo securityUserInfo) {
        return ConsentBO.builder()
                .consentBOConfig(
                        ConsentBOConfig
                                .builder()
                                .consentTypesRepository(consentTypesRepository)
                                .consentCustomerRepository(consentCustomerRepository)
                                .consentHistoryRepository(consentHistoryRepository)
                                .consentRepository(consentRepository)
                                .consentTypesRepository(consentTypesRepository)
                                .build()).build()
                .createOrUpdate(createOrdUpdateConsentRequest, securityUserInfo);
    }

    @Override
    public ConsentsResponse getConsentsByCustomer(Identifier identifier, SecurityUserInfo securityUserInfo) {
        return ConsentBO.builder()
                .consentBOConfig(
                        ConsentBOConfig
                                .builder()
                                .consentCustomerRepository(consentCustomerRepository)
                                .consentHistoryRepository(consentHistoryRepository)
                                .consentRepository(consentRepository)
                                .consentTypesRepository(consentTypesRepository)
                                .consentChannelRepository(consentChannelRepository)
                                .build()).build()
                .getConsentsByCustomer(identifier, securityUserInfo);
    }

    @Override
    public ConsentDetails getConsentDetailsByConsentName(String consentName) {
        return ConsentBO.builder()
                .consentBOConfig(
                        ConsentBOConfig
                                .builder()
                                .consentCustomerRepository(consentCustomerRepository)
                                .consentHistoryRepository(consentHistoryRepository)
                                .consentRepository(consentRepository)
                                .consentTypesRepository(consentTypesRepository)
                                .consentChannelRepository(consentChannelRepository)
                                .build()).build().getConsentDetailsByConsentName(consentName);

    }


    @Override
    public ConsentsResponse getConsentHistoryByCustomer(Identifier identifier, SecurityUserInfo securityUserInfo) {
        return ConsentBO.builder()
                .consentBOConfig(
                        ConsentBOConfig
                                .builder()
                                .consentCustomerRepository(consentCustomerRepository)
                                .consentHistoryRepository(consentHistoryRepository)
                                .consentChannelRepository(consentChannelRepository)
                                .build()).build()
                .getConsentHistoryByCustomer(identifier, securityUserInfo);
    }
}
