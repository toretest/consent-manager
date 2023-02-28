package no.senseon.consentmanager.rules;

import no.senseon.consentmanager.entities.ConsentTypes;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;

import java.util.List;
import java.util.stream.Collectors;

public final class ConsentTypeForCustomerRule {
    private  ConsentTypeForCustomerRule() {
    }

    public static List<ConsentTypes> filterOnRole(List<ConsentTypes> consentTypes, SecurityUserInfo securityUserInfo, String role){
        return consentTypes.stream() .filter(
                item -> {
                    if(securityUserInfo.getRoles().contains(role)){
                        return item.getDisplayToCustomer() ? true : false;
                    }else {
                        return true;
                    }
                }
        )
                .collect(Collectors.toList());
    }
}
