package no.senseon.consentmanager.restcontroller;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;*/
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import no.senseon.consentmanager.domain.ConsentTypesResponse;
import no.senseon.consentmanager.domain.CreateOrUpdateConsentTypesRequest;
import no.senseon.consentmanager.domain.CreateOrUpdateConsentTypesResponse;
import no.senseon.consentmanager.services.ConsentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@RequestMapping("/api/consenttypes")
/*@Api(tags = "Consenttypesservice", produces = "application/json")*/
public class ConsentTypesRestController {
    @Inject
    ConsentTypeService consentTypeService;

    @RequestMapping(
            value = "/consenttype",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST
    )
/*    @ApiOperation(
            value = "createOrUpdateConsentTypes",
            notes = "Create or update consent types")
    @Secured({"ROLE_MARKEDANSVARLIG", "ROLE_WEB", "ROLE_DEVELOPER"})*/
    public ResponseEntity<CreateOrUpdateConsentTypesResponse> createOrUpdateConsentTypes(@Valid @RequestBody CreateOrUpdateConsentTypesRequest createOrUpdateConsentTypesRequest) {
        CreateOrUpdateConsentTypesResponse createOrUpdateConsentTypesResponse = consentTypeService.createOrUpdateConsentTypes(createOrUpdateConsentTypesRequest);
        if (createOrUpdateConsentTypesResponse.isNewConsentTypesIndicator()) {
            return ResponseEntity.status(HttpStatus.valueOf(201)).body(consentTypeService.createOrUpdateConsentTypes(createOrUpdateConsentTypesRequest));
        } else {
            return ResponseEntity.ok(consentTypeService.createOrUpdateConsentTypes(createOrUpdateConsentTypesRequest));
        }
    }

    @RequestMapping(
            value = "/consentchannel/{consentChannelName}",
            produces = "application/json",
            method = RequestMethod.GET
    )
  /*  @ApiOperation(
            value = "getAllConsentTypes",
            notes = "Get all contenttypes")
    @Secured({"ROLE_MARKEDANSVARLIG","ROLE_WEB", "ROLE_DEVELOPER"})*/
    public ResponseEntity<ConsentTypesResponse> getAllConsentTypes(@PathVariable String consentChannelName) {
        return ResponseEntity.ok(consentTypeService.getAllConsentTypes(consentChannelName));
    }

    @RequestMapping(
            value = "/consenttype/{consentTypeName}/{consentChannelName}/consenttypeshistory",
            produces = "application/json",
            method = RequestMethod.GET
    )
   /* @ApiOperation(
            value = "getConsentTypesHistoryByConsentTypeNameAndChannelName",
            notes = "Get Consenttypes by typename and consent type channel")
    @Secured({"ROLE_MARKEDANSVARLIG", "ROLE_WEB", "ROLE_DEVELOPER"})*/
    public ResponseEntity<ConsentTypesResponse> getCosentTypesHistoryByConsentTypeNameAndChannelName(@PathVariable String consentTypeName, @NotNull @PathVariable String consentChannelName) {
        return ResponseEntity.ok(consentTypeService.getConsentTypesHistoryByConsentTypeNameAndChannelName(consentTypeName, consentChannelName));
    }

}
