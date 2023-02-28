package no.ya.topaz.consentmanager.repositories;

import no.ya.topaz.consentmanager.entity.Consent;
import no.ya.topaz.consentmanager.entity.ConsentCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
@SuppressWarnings("squid:S00100")
public interface ConsentRepository extends JpaRepository<Consent, Long> {
    List<Consent> findConsentsByConsentCustomer(ConsentCustomer consentCustomer);
    List<Consent> findConsentsByConsentCustomerAndConsentTypes_ConsentChannel_Name(ConsentCustomer consentCustomer,String consentTypesChannelName);
}
