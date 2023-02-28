package no.senseon.consentmanager.business;

import lombok.Builder;
import lombok.Getter;
import no.senseon.consentmanager.domain.ConsentDetails;
import no.senseon.consentmanager.domain.ConsentsResponse;
import no.senseon.consentmanager.domain.CreateOrdUpdateConsentRequest;
import no.senseon.consentmanager.domain.Identifier;
import no.senseon.consentmanager.entities.*;
import no.senseon.consentmanager.rules.IdentifierRule;
import no.senseon.consentmanager.rules.SsnRule;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;
import no.senseon.consentmanager.utilities.ConsentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Builder
@Getter
public class ConsentBO {
    private ConsentBOConfig consentBOConfig;
    private static final String ROLE_KUNDE = "ROLE_KUNDE";


    public ConsentBO(ConsentBOConfig consentBOConfig) {
        this.consentBOConfig = consentBOConfig;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ConsentsResponse createOrUpdate(CreateOrdUpdateConsentRequest createOrdUpdateConsentRequest, SecurityUserInfo securityUserInfo) {
        Identifier identifier = createOrdUpdateConsentRequest.getIdentifier();
        IdentifierRule.validateContactConsentsIdentifier(identifier);
        SsnRule.isTheSame(identifier, securityUserInfo);

        List<ConsentTypes> consentTypesDefault = consentBOConfig.getConsentTypesRepository().findConsentTypesByConsentChannel_Name(createOrdUpdateConsentRequest.getIdentifier().getConsentTypesChannel());
        ConsentCustomer consentCustomer = consentBOConfig.getConsentCustomerRepository().findConsentCustomerByIdentifierTypeAndIdentifier(identifier.getIdentifierType(), identifier.getIdentifier());
        if (consentCustomer == null) {
            consentCustomer = createNewCustomerWithDefaultConsentTypes(identifier, consentTypesDefault);
        } else {
            addNewConsentTypesToExistingCustomer(identifier.getConsentTypesChannel(), consentCustomer, consentTypesDefault);
        }
        updateCustomerConsentByContactConsentsRequest(consentCustomer, createOrdUpdateConsentRequest);
        return getConsentsByCustomer(identifier).filterCustomerAllowedConsentTypes(securityUserInfo);
    }

    public ConsentsResponse getConsentsByCustomer(Identifier identifier, SecurityUserInfo securityUserInfo) {
        IdentifierRule.validateContactConsentsIdentifier(identifier);
        SsnRule.isTheSame(identifier, securityUserInfo);
        return getConsentsByCustomer(identifier).filterCustomerAllowedConsentTypes(securityUserInfo);
    }

    public ConsentDetails getConsentDetailsByConsentName(String consentName) {
        ConsentTypes consentTypes = consentBOConfig.getConsentTypesRepository().findConsentTypesByName(consentName);
        if (consentTypes == null || !consentTypes.getDisplayToCustomer()) {
            return null;
        } else {
            return new ConsentDetails().builder().consentName(consentTypes.getName())
                    .consentText(consentTypes.getConsentText())
                    .description(consentTypes.getDescription())
                    .displayName(consentTypes.getDisplayName())
                    .consentIndicator(consentTypes.getConsentDefaultIndicator())
                    .consentName(consentTypes.getName()).build();
        }
    }


    public ConsentsResponse getConsentHistoryByCustomer(Identifier identifier, SecurityUserInfo securityUserInfo) {
        IdentifierRule.validateContactConsentsIdentifier(identifier);
        SsnRule.isTheSame(identifier, securityUserInfo);
        ConsentsResponse consentsResponse = ConsentsResponse.builder().identifier(identifier).build();
        ConsentCustomer consentCustomer = consentBOConfig.getConsentCustomerRepository().findConsentCustomerByIdentifierTypeAndIdentifier(identifier.getIdentifierType(), identifier.getIdentifier());
        ConsentChannel consentChannel = consentBOConfig.getConsentChannelRepository().findConsentChannelByName(identifier.getConsentTypesChannel());

        List<ConsentHistory> consentHistories = consentBOConfig
                .getConsentHistoryRepository()
                .findConsentHistoriesByConsent_ConsentTypes_ConsentChannelAndConsent_ConsentCustomer(consentChannel, consentCustomer);
        consentsResponse.setConsentHistoryList(consentHistories);
        return consentsResponse;
    }

    private ConsentsResponse getConsentsByCustomer(Identifier identifier) {
        ConsentsResponse consentsResponse;

        IdentifierRule.validateContactConsentsIdentifier(identifier);

        List<ConsentTypes> defaultConsentTypes = consentBOConfig.getConsentTypesRepository().findConsentTypesByConsentChannel_Name(identifier.getConsentTypesChannel());
        ConsentCustomer consentCustomer = consentBOConfig.getConsentCustomerRepository().findConsentCustomerByIdentifierTypeAndIdentifier(identifier.getIdentifierType(), identifier.getIdentifier());
        if (consentCustomer != null) {
            consentsResponse = getConsentByCustomerWithNewConsentTypesAdded(consentCustomer, identifier.getConsentTypesChannel(), defaultConsentTypes);

            List<Consent> consents = consentBOConfig.getConsentRepository().findConsentsByConsentCustomerAndConsentTypes_ConsentChannel_Name(consentCustomer, identifier.getConsentTypesChannel());
            if (consents == null || consents.isEmpty()) {
                consentsResponse.setIdentifierExist(false);
            } else {
                consentsResponse.setIdentifierExist(true);
            }

        } else {
            final ConsentsResponse newConsentsResponse = ConsentsResponse.builder().identifier(identifier).build();
            newConsentsResponse.setIdentifierExist(false);
            newConsentsResponse.setConsentDetailsMap(new HashMap<>());
            defaultConsentTypes.forEach(
                    consentTypesDefaultItem -> {
                        ConsentDetails consentDetails = mapFromConsentTypes(consentTypesDefaultItem);
                        newConsentsResponse.getConsentDetailsMap().put(consentDetails.getConsentName(), consentDetails);
                    }
            );
            consentsResponse = newConsentsResponse;
        }

        return consentsResponse;
    }

    @Transactional
    public void addNewConsentTypesToExistingCustomer(String consentTypesChannel, ConsentCustomer consentCustomer, List<ConsentTypes> consentTypesDefault) {
        List<Consent> customerConsents = consentBOConfig.getConsentRepository().findConsentsByConsentCustomer(consentCustomer);
        consentTypesDefault.forEach(
                consentTypes -> {
                    if (ConsentUtil.findConsentInListByConsentTypesName(consentTypes.getName(), customerConsents) == null) {
                        ConsentTypes newConsentTypes = consentBOConfig.getConsentTypesRepository().findConsentTypesByNameAndConsentChannel_Name(consentTypes.getName(), consentTypesChannel);
                        Consent consentToSave = new Consent();
                        consentToSave.setConsentIndicator(newConsentTypes.getConsentDefaultIndicator());
                        consentToSave.setConsentCustomer(consentCustomer);
                        consentToSave.setConsentTypes(newConsentTypes);
                        consentBOConfig.getConsentRepository().save(consentToSave);
                    }
                }
        );
    }

    @Transactional
    public void updateCustomerConsentByContactConsentsRequest(ConsentCustomer consentCustomer, CreateOrdUpdateConsentRequest createOrdUpdateConsentRequest) {
        List<Consent> consentsByCustomers = consentBOConfig.getConsentRepository().findConsentsByConsentCustomer(consentCustomer);

        if (createOrdUpdateConsentRequest.getConsentIndicatorsToCreateOrUpdate() != null) {
            createOrdUpdateConsentRequest.getConsentIndicatorsToCreateOrUpdate().forEach(
                    (key, value) -> {
                        Consent consentToUpdate = ConsentUtil.findConsentInListByConsentTypesName(key, consentsByCustomers);
                        if (consentToUpdate != null) {
                            consentToUpdate.setConsentIndicator(value);
                            consentBOConfig.getConsentRepository().saveAndFlush(consentToUpdate);
                        }
                    }
            );
        }
    }

    @Transactional
    public ConsentCustomer createNewCustomerWithDefaultConsentTypes(Identifier identifier, List<ConsentTypes> defaultConsentTypes) {
        ConsentCustomer consentCustomer = ConsentCustomer.builder()
                .identifier(identifier.getIdentifier())
                .identifierType(identifier.getIdentifierType())
                .build();
        consentBOConfig.getConsentCustomerRepository().saveAndFlush(consentCustomer);
        defaultConsentTypes.forEach(
                consentTypesDefaultItem -> {
                    Consent consentToSave = new Consent();
                    consentToSave.setConsentIndicator(consentTypesDefaultItem.getConsentDefaultIndicator());
                    consentToSave.setConsentCustomer(consentCustomer);
                    consentToSave.setConsentTypes(consentTypesDefaultItem);
                    consentBOConfig.getConsentRepository().saveAndFlush(consentToSave);
                }
        );
        return consentCustomer;
    }


    private ConsentsResponse getConsentByCustomerWithNewConsentTypesAdded(ConsentCustomer consentCustomer, String consentTypesChannelName, List<ConsentTypes> consentTypesDefault) {
        final ConsentsResponse consentsResponse = getConsentsByCustomer(consentCustomer, consentTypesChannelName);
        if (consentTypesDefault != null) {
            consentTypesDefault.forEach(
                    consentTypes -> {
                        boolean consentAlreadyExistIndicator = consentsResponse.getConsentDetailsMap().containsKey(consentTypes.getName());
                        if (!consentAlreadyExistIndicator) {
                            ConsentDetails newConsentDetails = mapFromConsentTypes(consentTypes);
                            consentsResponse.getConsentDetailsMap().put(consentTypes.getName(), newConsentDetails);
                        }
                    }
            );
        }
        return consentsResponse;
    }

    private ConsentsResponse getConsentsByCustomer(ConsentCustomer consentCustomer, String consentTypesChannelName) {
        ConsentsResponse consentsResponse = ConsentsResponse.builder()
                .identifier(Identifier.builder()
                        .identifier(consentCustomer.getIdentifier())
                        .identifierType(consentCustomer.getIdentifierType())
                        .consentTypesChannel(consentTypesChannelName)
                        .build())
                .consentDetailsMap(new HashMap<>())
                .build();
        List<Consent> consents = consentBOConfig.getConsentRepository().findConsentsByConsentCustomerAndConsentTypes_ConsentChannel_Name(consentCustomer, consentTypesChannelName);
        if (consents != null && !consents.isEmpty()) {
            consents.forEach(
                    o -> {
                        ConsentDetails consentDetails = mapFromConsentTypes(o.getConsentTypes());
                        consentDetails.setConsentIndicator(o.getConsentIndicator());
                        consentsResponse.getConsentDetailsMap().put(consentDetails.getConsentName(), consentDetails);

                    }
            );
        }

        return consentsResponse;
    }

    private ConsentDetails mapFromConsentTypes(ConsentTypes consentTypes) {
        return ConsentDetails.builder()
                .consentName(consentTypes.getName())
                .displayName(consentTypes.getDisplayName())
                .consentText(consentTypes.getConsentText())
                .description(consentTypes.getDescription())
                .displayToCustomer(consentTypes.getDisplayToCustomer())
                .build();
    }
}
