package no.senseon.consentmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import no.senseon.consentmanager.entities.ConsentHistory;
import no.senseon.consentmanager.rules.ConsentCustomerRule;
import no.senseon.consentmanager.springcomponents.SecurityUserInfo;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsentsResponse {
    @NotNull(message = "identifier is null. Must provide a email or ssn number.")
    private Identifier identifier;
   // @ApiModelProperty(dataType = "[Lno.senseon.consentmanager.domain.ConsentDetails;")
    private Map<String, ConsentDetails> consentDetailsMap;
    private List<ConsentHistory> consentHistoryList;
    @JsonIgnore
    private boolean identifierExist;

    public ConsentsResponse filterCustomerAllowedConsentTypes(SecurityUserInfo securityUserInfo){
        consentDetailsMap = ConsentCustomerRule
                .filterOnRole(
                        consentDetailsMap,
                        securityUserInfo,"ROLE_KUNDE");
        return this;
    }
}
