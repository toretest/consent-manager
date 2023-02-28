package no.senseon.consentmanager.rules;

import no.senseon.consentmanager.domain.Identifier;
import no.senseon.consentmanager.domain.Roles;
import no.senseon.consentmanager.entities.IdentifierType;
import no.senseon.consentmanager.exception.AppForbiddenException;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;

public final class SsnRule {
    public static final String NOT_EQUAL_SSN ="ssn number from security context is not the same as requested ssn for kunde role";

    private SsnRule() {
    }

    public static void isTheSame(Identifier identifier, SecurityUserInfo securityUserInfo) {
        if(identifier.getIdentifierType() == IdentifierType.SSN && ConsentCustomerRule.HasRole(securityUserInfo, Roles.ROLE_KUNDE.toString())){
            if ( (identifier.getIdentifier() == null || securityUserInfo.getSsn() == null) || !identifier.getIdentifier().trim().equals(securityUserInfo.getSsn().trim())) {
                throw new AppForbiddenException(NOT_EQUAL_SSN);
            }
        }



    }
}
