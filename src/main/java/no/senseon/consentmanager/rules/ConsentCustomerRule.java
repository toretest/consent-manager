package no.senseon.consentmanager.rules;

import no.senseon.consentmanager.domain.ConsentDetails;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;

import java.util.Map;
import java.util.stream.Collectors;

public final class ConsentCustomerRule {
    private  ConsentCustomerRule() {
    }

    public static Map<String, ConsentDetails> filterOnRole(Map<String, ConsentDetails> consentDetailsMapToFilter, SecurityUserInfo securityUserInfo, String role) {
        return consentDetailsMapToFilter.entrySet().stream().filter(
                item -> {
                    if (securityUserInfo.getRoles().contains(role)) {
                        return item.getValue().getDisplayToCustomer() ? true : false;
                    } else {
                        return true;
                    }
                }
        )
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    public static boolean HasRole(SecurityUserInfo securityUserInfo, String role){
        return securityUserInfo.getRoles().contains(role);
    }
}
