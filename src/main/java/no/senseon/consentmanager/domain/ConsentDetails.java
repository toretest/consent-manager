package no.senseon.consentmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsentDetails {
    @NotNull(message = "consentName is null ConsentDetails")
    @NotEmpty(message = "consentName must have a value in ConsentDetails")
    private String consentName;
    private String displayName;
    private String consentText;
    private String description;
    private boolean consentIndicator;
    @JsonIgnore
    private Boolean displayToCustomer;
}
