package no.senseon.consentmanager.springcomponents;

public interface IAuthenticationFacade {
    SecurityUserInfo getUserInfo();
    boolean hasRole(String role);
}
