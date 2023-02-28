package no.ya.topaz.consentmanager.config;

import no.ya.topaz.consentmanager.springcomponents.IAuthenticationFacade;
import org.springframework.data.domain.AuditorAware;

import javax.inject.Inject;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Inject
    IAuthenticationFacade authenticationFacade;

    @Override
    public String getCurrentAuditor() {
        return authenticationFacade.getUserInfo().getUserId();
    }
}