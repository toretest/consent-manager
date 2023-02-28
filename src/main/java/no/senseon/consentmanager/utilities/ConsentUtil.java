package no.senseon.consentmanager.utilities;

import no.senseon.consentmanager.entities.Consent;

import java.util.List;

public class ConsentUtil {
    private ConsentUtil() {
    }

    public static Consent findConsentInListByConsentTypesName(String name, List<Consent> consents) {
        return consents.stream().filter(o -> o.getConsentTypes().getName().equals(name)).findFirst().orElse(null);
    }
}
