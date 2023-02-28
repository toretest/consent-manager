package no.senseon.consentmanager.services;

import no.senseon.consentmanager.business.ConsentTypesBO;
import no.senseon.consentmanager.business.ConsentTypesBOConfig;
import no.senseon.consentmanager.domain.ConsentTypesResponse;
import no.senseon.consentmanager.domain.CreateOrUpdateConsentTypesRequest;
import no.senseon.consentmanager.domain.CreateOrUpdateConsentTypesResponse;
import no.senseon.consentmanager.repositories.*;
import org.springframework.stereotype.Service;
import javax.inject.Inject;

@Service
public class ConsentTypeServiceImpl implements ConsentTypeService{
    private final ConsentTypesRepository consentTypesRepository;
    private final ConsentChannelRepository consentChannelRepository;
    private final ConsentTypesHistoryRepository consentTypesHistoryRepository;

    @Inject
    public ConsentTypeServiceImpl(
                              ConsentTypesRepository consentTypesRepository,
                              ConsentChannelRepository consentChannelRepository,
                              ConsentTypesHistoryRepository consentTypesHistoryRepository) {
        this.consentTypesRepository = consentTypesRepository;
        this.consentChannelRepository = consentChannelRepository;
        this.consentTypesHistoryRepository = consentTypesHistoryRepository;
    }

    @Override
    public CreateOrUpdateConsentTypesResponse createOrUpdateConsentTypes(CreateOrUpdateConsentTypesRequest createOrUpdateConsentTypesRequest) {
        return ConsentTypesBO.builder()
                .consentTypesBOConfig(
                        ConsentTypesBOConfig
                                .builder()
                                .consentChannelRepository(consentChannelRepository)
                                .consentTypesHistoryRepository(consentTypesHistoryRepository)
                                .consentTypesRepository(consentTypesRepository)
                                .build()).build()
                .createOrUpdateConsentTypes(createOrUpdateConsentTypesRequest);
    }

    @Override
    public ConsentTypesResponse getAllConsentTypes(String consentChannelName) {
        return ConsentTypesBO.builder()
                .consentTypesBOConfig(
                        ConsentTypesBOConfig
                                .builder()
                                .consentChannelRepository(consentChannelRepository)
                                .consentTypesHistoryRepository(consentTypesHistoryRepository)
                                .consentTypesRepository(consentTypesRepository)
                                .build()).build()
                .getAllConsentTypes(consentChannelName);
    }

    @Override
    public ConsentTypesResponse getConsentTypesHistoryByConsentTypeNameAndChannelName(String consentTypeName, String channelName) {
        return ConsentTypesBO.builder()
                .consentTypesBOConfig(
                        ConsentTypesBOConfig
                                .builder()
                                .consentChannelRepository(consentChannelRepository)
                                .consentTypesHistoryRepository(consentTypesHistoryRepository)
                                .consentTypesRepository(consentTypesRepository)
                                .build()).build()
                .getConsentTypesHistoryByConsentTypeNameAndChannelName(consentTypeName,channelName);
    }
}
