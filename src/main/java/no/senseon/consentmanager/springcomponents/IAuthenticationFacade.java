package no.ya.topaz.consentmanager.springcomponents;

public interface IAuthenticationFacade {
    SecurityUserInfo getUserInfo();
    boolean hasRole(String role);
}
