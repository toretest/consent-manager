package no.senseon.consentmanager.domain;

//import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrdUpdateConsentRequest {
   // @ApiModelProperty(position = 0)
    @NotNull(message = "identifier is null. Must provide a email or ssn number.")
    private Identifier identifier;
    //@ApiModelProperty(position = 1, dataType = "[Ljava.lang.HasMap<String,Boolean>;", example = "{\"Kunde 1\" : false, \"Nyhetsbrev\": false }")
    private Map<String, Boolean> consentIndicatorsToCreateOrUpdate;

}
