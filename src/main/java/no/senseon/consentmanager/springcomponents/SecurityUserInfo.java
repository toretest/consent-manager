package no.senseon.consentmanager.springcomponents;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class SecurityUserInfo {
    private String ssn;
    private String email;
    private String userId;
    private Set<String> roles;
}
