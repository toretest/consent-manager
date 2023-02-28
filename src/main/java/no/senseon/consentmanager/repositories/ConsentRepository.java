package no.senseon.consentmanager.repositories;

import no.senseon.consentmanager.entities.Consent;
import no.senseon.consentmanager.entities.ConsentCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsentRepository extends JpaRepository<Consent, Long> {
    List<Consent> findConsentsByConsentCustomer(ConsentCustomer consentCustomer);
    List<Consent> findConsentsByConsentCustomerAndConsentTypes_ConsentChannel_Name(ConsentCustomer consentCustomer,String consentTypesChannelName);
}
