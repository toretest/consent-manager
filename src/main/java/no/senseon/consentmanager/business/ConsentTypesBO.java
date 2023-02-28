package no.senseon.consentmanager.business;

import lombok.Builder;
import lombok.Getter;
import no.senseon.consentmanager.domain.*;
import no.senseon.consentmanager.entities.ConsentChannel;
import no.senseon.consentmanager.entities.ConsentTypes;
import no.senseon.consentmanager.entities.ConsentTypesHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ConsentTypesBO {
    private final ConsentTypesBOConfig consentTypesBOConfig;

    public ConsentTypesBO(ConsentTypesBOConfig consentTypesBOConfig) {
        this.consentTypesBOConfig = consentTypesBOConfig;
    }

    public ConsentTypesResponse getAllConsentTypes(String consentChannelName) {
        ConsentTypesResponse consentTypesResponse = ConsentTypesResponse.builder().build();
        consentTypesResponse.setConsentTypes(new ArrayList<>());
        List<ConsentTypes> consentTypes = consentTypesBOConfig.getConsentTypesRepository().findConsentTypesByConsentChannel_Name(consentChannelName);
        consentTypes.forEach(
                o ->
                        consentTypesResponse.getConsentTypes().add(
                                ConsentType.builder()
                                        .consentTypesDetail(ConsentTypesDetail
                                                .builder()
                                                .consentDefaultIndicator(o.getConsentDefaultIndicator())
                                                .displayToCustomer(o.getDisplayToCustomer())
                                                .consentText(o.getConsentText())
                                                .description(o.getDescription())
                                                .displayName(o.getDisplayName())
                                                .build())
                                        .consentTypesChannelIdentifier(
                                                ConsentTypesChannelIdentifier.builder()
                                                        .consentChannelName(o.getConsentChannel().getName())
                                                        .consentTypeName(o.getName())
                                                        .build())
                                        .build())
        );
        return consentTypesResponse;
    }

    public ConsentTypesResponse getConsentTypesHistoryByConsentTypeNameAndChannelName(String consentTypeName, String channelName) {
        ConsentTypesResponse consentTypesResponse = ConsentTypesResponse.builder().build();
        consentTypesResponse.setConsentTypes(new ArrayList<>());
        ConsentTypes consentTypes = consentTypesBOConfig.getConsentTypesRepository().findConsentTypesByNameAndConsentChannel_Name(consentTypeName, channelName);
        if (consentTypes != null) {
            List<ConsentTypesHistory> consentTypesHistories = consentTypesBOConfig.getConsentTypesHistoryRepository().findConsentTypesHistoriesByConsentTypes(consentTypes);
            consentTypesResponse.getConsentTypes().add(
                    ConsentType.builder()
                            .consentTypesDetail(ConsentTypesDetail
                                    .builder().consentDefaultIndicator(consentTypes.getConsentDefaultIndicator())
                                    .displayName(consentTypes.getDisplayName())
                                    .displayToCustomer(consentTypes.getDisplayToCustomer())
                                    .consentText(consentTypes.getConsentText())
                                    .description(consentTypes.getDescription())
                                    .build())
                            .consentTypesChannelIdentifier(
                                    ConsentTypesChannelIdentifier.builder()
                                            .consentChannelName(channelName)
                                            .consentTypeName(consentTypeName)
                                            .build())
                            .consentTypesHistories(consentTypesHistories)
                            .build());
        }
        return consentTypesResponse;
    }

    @Transactional
    public CreateOrUpdateConsentTypesResponse createOrUpdateConsentTypes(CreateOrUpdateConsentTypesRequest createOrUpdateConsentTypesRequest) {
        ConsentChannel consentChannel = consentTypesBOConfig.getConsentChannelRepository().findConsentChannelByName(createOrUpdateConsentTypesRequest.getConsentTypesChannelIdentifier().getConsentChannelName());
        ConsentTypes consentTypes = consentTypesBOConfig.getConsentTypesRepository().findConsentTypesByName(createOrUpdateConsentTypesRequest.getConsentTypesChannelIdentifier().getConsentTypeName());
        CreateOrUpdateConsentTypesResponse createOrUpdateConsentTypesResponse =
                CreateOrUpdateConsentTypesResponse
                        .builder()
                        .consentTypesChannelIdentifier(createOrUpdateConsentTypesRequest.getConsentTypesChannelIdentifier())
                        .consentTypesHistories(new ArrayList<>()).build();
        if (consentChannel != null) {
            if (consentTypes == null) {
                ConsentTypes newConsentTypes = new ConsentTypes();
                newConsentTypes.setName(createOrUpdateConsentTypesRequest.getConsentTypesChannelIdentifier().getConsentTypeName());
                newConsentTypes.setConsentChannel(consentChannel);
                newConsentTypes.setConsentDefaultIndicator(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getConsentDefaultIndicator());
                newConsentTypes.setConsentText(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getConsentText());
                newConsentTypes.setDisplayName(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getDisplayName());
                newConsentTypes.setDescription(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getDescription());
                newConsentTypes.setDisplayToCustomer(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getDisplayToCustomer());
                consentTypes = consentTypesBOConfig.getConsentTypesRepository().save(newConsentTypes);

                ConsentTypesDetail newConsentTypesDetail = ConsentTypesDetail.builder()
                        .consentDefaultIndicator(newConsentTypes.getConsentDefaultIndicator())
                        .description(newConsentTypes.getDescription())
                        .consentText(newConsentTypes.getConsentText())
                        .displayName(newConsentTypes.getDisplayName())
                        .displayToCustomer(newConsentTypes.getDisplayToCustomer())
                        .build();
                createOrUpdateConsentTypesResponse = CreateOrUpdateConsentTypesResponse.builder()
                        .consentTypesChannelIdentifier(createOrUpdateConsentTypesRequest.getConsentTypesChannelIdentifier())
                        .consentTypesDetail(newConsentTypesDetail)
                        .newConsentTypesIndicator(true)
                        .build();
            } else {
                consentTypes.setConsentDefaultIndicator(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getConsentDefaultIndicator());
                consentTypes.setConsentText(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getConsentText());
                consentTypes.setDisplayName(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getDisplayName());
                consentTypes.setDescription(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getDescription());
                consentTypes.setDisplayToCustomer(createOrUpdateConsentTypesRequest.getConsentTypesDetail().getDisplayToCustomer());
                consentTypesBOConfig.getConsentTypesRepository().save(consentTypes);

                ConsentTypesDetail newConsentTypesDetail = ConsentTypesDetail.builder()
                        .consentDefaultIndicator(consentTypes.getConsentDefaultIndicator())
                        .description(consentTypes.getDescription())
                        .consentText(consentTypes.getConsentText())
                        .displayName(consentTypes.getDisplayName())
                        .displayToCustomer(consentTypes.getDisplayToCustomer())
                        .build();
                createOrUpdateConsentTypesResponse = CreateOrUpdateConsentTypesResponse.builder()
                        .consentTypesChannelIdentifier(createOrUpdateConsentTypesRequest.getConsentTypesChannelIdentifier())
                        .consentTypesDetail(newConsentTypesDetail)
                        .newConsentTypesIndicator(false)
                        .build();
            }
        }
        final List<ConsentTypesHistory> consentTypesHistoriesToAddInResponse = new ArrayList<>();
        List<ConsentTypesHistory> consentTypesHistories = consentTypesBOConfig.getConsentTypesHistoryRepository().findConsentTypesHistoriesByConsentTypes(consentTypes);
        consentTypesHistories.forEach(o -> {
            if (o != null) {
                consentTypesHistoriesToAddInResponse.add(o);
            }
        });
        createOrUpdateConsentTypesResponse.setConsentTypesHistories(consentTypesHistoriesToAddInResponse);
        return createOrUpdateConsentTypesResponse;
    }
}
