package no.senseon.consentmanager.repositories;

import no.senseon.consentmanager.entities.ConsentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsentTypesRepository extends JpaRepository<ConsentTypes, Long> {
    ConsentTypes findConsentTypesByName(String name);
    ConsentTypes findConsentTypesByNameAndConsentChannel_Name(String consentTypesName, String channelName);
    List<ConsentTypes> findConsentTypesByConsentChannel_Name(String channelName);

}
