package no.senseon.consentmanager.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsentTypesChannelIdentifier {
    @NotNull(message = "can not be null")
    @Length(max = 20,message = "can only be 20 characters and unique per channel")
    private String consentTypeName;
    @NotNull(message = "channel must already exist and can not be null")
    private String consentChannelName;
}
