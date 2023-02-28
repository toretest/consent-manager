package no.senseon.consentmanager.rules;

import no.senseon.consentmanager.domain.Identifier;
import no.senseon.consentmanager.entities.IdentifierType;
import no.senseon.consentmanager.exception.AppBadRequestException;
import no.senseon.consentmanager.utilities.EmailUtility;
import no.senseon.consentmanager.utilities.SsnUtility;

public final class IdentifierRule {
    public static final String VALIDATION_SSN_ERROR = "Ssn %s must be valid when creating a new ConsentCustomer.";
    public static final String VALIDATION_EMAIL_ERROR = "Email %s must be valid when creating a new ConsentCustomer.";
    public static final String VALIDATION_IDENTIFIER_ERROR = "Can not create a ConsentCustomer without a ssn or email identifier.";

    private IdentifierRule() {
    }

    public static void validateContactConsentsIdentifier(Identifier identifier) {
        if(identifier == null || identifier.getIdentifier()==null ){
            throw new AppBadRequestException(String.format(VALIDATION_IDENTIFIER_ERROR));
        }else if (identifier.getIdentifierType()== IdentifierType.SSN && !SsnUtility.validateSsn(identifier.getIdentifier())) {
            throw new AppBadRequestException(String.format(VALIDATION_SSN_ERROR, identifier.getIdentifier()));
        }else if(identifier.getIdentifierType()== IdentifierType.EMAIL && !EmailUtility.validateEmailAddress(identifier.getIdentifier())){
            throw new AppBadRequestException(String.format(VALIDATION_EMAIL_ERROR, identifier.getIdentifier()));
        }
    }
}
