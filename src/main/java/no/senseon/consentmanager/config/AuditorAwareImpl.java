package no.senseon.consentmanager.config;

import no.senseon.consentmanager.springcomponents.IAuthenticationFacade;
import org.springframework.data.domain.AuditorAware;

import javax.inject.Inject;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Inject
    IAuthenticationFacade authenticationFacade;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(authenticationFacade.getUserInfo().getUserId());
    }
}
