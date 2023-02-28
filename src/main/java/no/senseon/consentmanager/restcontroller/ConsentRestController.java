package no.senseon.consentmanager.restcontroller;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;*/
import jakarta.validation.Valid;
import no.senseon.consentmanager.domain.ConsentDetails;
import no.senseon.consentmanager.domain.ConsentsResponse;
import no.senseon.consentmanager.domain.CreateOrdUpdateConsentRequest;
import no.senseon.consentmanager.domain.Identifier;
import no.senseon.consentmanager.entities.IdentifierType;
import no.senseon.consentmanager.services.ConsentService;
import no.senseon.consentmanager.springcomponents.IAuthenticationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;

@RestController
@RequestMapping("/api/consents")
//@Api(tags = "Consentsservice", produces = "application/json")
public class ConsentRestController {
    @Inject
    ConsentService consentService;
    @Inject
    IAuthenticationFacade authenticationFacade;

    @RequestMapping(
            value = "/consent",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST
    )
/*    @ApiOperation(
            value = "createOrUpdate",
            notes = "create or update consents by customer given an identifier email or ssn")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated", response = ConsentsResponse.class),
            @ApiResponse(code = 201, message = "Created", response = ConsentsResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ConsentsResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ConsentsResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ConsentsResponse.class),
    })*/
    //@Secured({"ROLE_KUNDEBEHANDLER", "ROLE_KUNDE", "ROLE_WEB", "ROLE_DEVELOPER"})
    public ResponseEntity<ConsentsResponse> createOrUpdate(@Valid @RequestBody CreateOrdUpdateConsentRequest createOrdUpdateConsentRequest) {

        ConsentsResponse consentsResponseResponse = consentService.createOrUpdate(createOrdUpdateConsentRequest, authenticationFacade.getUserInfo());
        return ResponseEntity.ok(consentsResponseResponse);
    }

    @RequestMapping(
            value = "/consent/customer/{identifier}/{identifierType}/{channelname}",
            produces = "application/json",
            method = RequestMethod.GET
    )
/*    @ApiOperation(
            value = "getConsentByCustomer",
            notes = "Get all consents by customer given an identifier email or ssn and a consentchannel")
    @Secured({"ROLE_KUNDEBEHANDLER", "ROLE_KUNDE", "ROLE_WEB", "ROLE_DEVELOPER"})*/
    public ResponseEntity<ConsentsResponse> getConsentByCustomer(
            @PathVariable String identifier, @PathVariable IdentifierType identifierType, @PathVariable String channelname) {
        ConsentsResponse consentsResponse = consentService
                .getConsentsByCustomer(
                        Identifier
                                .builder()
                                .identifier(identifier)
                                .identifierType(identifierType)
                                .consentTypesChannel(channelname)
                                .build(), authenticationFacade.getUserInfo());
        if (!consentsResponse.isIdentifierExist()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(consentsResponse);
        } else {
            return ResponseEntity.ok(consentsResponse);
        }
    }

    @RequestMapping(value = "/consent/{consentName}")
    //@ApiOperation(value = "getConsentDetails")
    //@Secured({"ROLE_PUBLIC"})
    public ResponseEntity<ConsentDetails> getConsentByConsentName(@PathVariable String consentName) {
        ConsentDetails consentDetails = consentService.getConsentDetailsByConsentName(consentName);
        if (consentDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(consentDetails);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(consentDetails);
        }
    }

    @RequestMapping(
            value = "/consent/customer/{identifier}/{identifierType}/consentchannel/{channelname}/consenthistory",
            produces = "application/json",
            method = RequestMethod.GET
    )
  /*  @ApiOperation(
            value = "getConsentHistoryByCustomer",
            notes = "Get consent history audit log by identifier email or ssn")
    @Secured({"ROLE_KUNDEBEHANDLER", "ROLE_KUNDE", "ROLE_WEB", "ROLE_DEVELOPER"})*/
    public ResponseEntity<ConsentsResponse> getConsentHistoryByCustomer(
            @PathVariable String identifier, @PathVariable IdentifierType identifierType, @PathVariable String channelname) {
        return ResponseEntity.ok(
                consentService
                        .getConsentHistoryByCustomer(
                                Identifier
                                        .builder()
                                        .identifier(identifier)
                                        .identifierType(identifierType)
                                        .consentTypesChannel(channelname)
                                        .build(), authenticationFacade.getUserInfo()));
    }


}
