package no.ya.topaz.consentmanager.springcomponents;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@SuppressWarnings("unchecked")
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public SecurityUserInfo getUserInfo() {

        SecurityUserInfo user = SecurityUserInfo.builder().userId("").ssn("").build();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
                KeycloakPrincipal<KeycloakSecurityContext> kp = getPrincipal();
                if (kp != null) {
                    user.setUserId(kp.getKeycloakSecurityContext().getToken().getPreferredUsername());
                    user.setEmail(kp.getKeycloakSecurityContext().getToken().getEmail());
                    user.setRoles(getRoles(authentication));
                    Object opt = Optional.ofNullable(kp.getKeycloakSecurityContext().getToken().getOtherClaims().get("ssn")).orElse("");
                    user.setSsn((String) opt);
                }
        }
        return user;
    }

    @Override
    public boolean hasRole(String role) {
        KeycloakPrincipal<KeycloakSecurityContext> kp = getPrincipal();
        if (kp != null) {
            return kp.getKeycloakSecurityContext().getAuthorizationContext().hasResourcePermission(role);
        }
        return false;
    }

    private Set<String> getRoles(Authentication authentication) {
        Set<String> roles = new HashSet<>();
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        authorities.forEach(o -> roles.add(o.getAuthority())
        );
        return roles;
    }

    private KeycloakPrincipal<KeycloakSecurityContext> getPrincipal() {
        KeycloakPrincipal<KeycloakSecurityContext> kp = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
                kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
        }
        return kp;
    }

}
