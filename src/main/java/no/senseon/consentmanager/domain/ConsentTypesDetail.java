package no.senseon.consentmanager.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsentTypesDetail {
    @NotNull
    @Length(max = 50, message = "can only be 50 characters")
    private String displayName;
    private Boolean displayToCustomer;
    private Boolean consentDefaultIndicator;
    @Length(max = 300, message = "can only be 300 characters")
    private String consentText;
    @Length(max = 4000, message = "can only be 4000 characters")
    private String description;

}
