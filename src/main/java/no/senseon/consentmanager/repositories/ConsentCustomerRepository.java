package no.senseon.consentmanager.repositories;

import no.senseon.consentmanager.entities.ConsentCustomer;
import no.senseon.consentmanager.entities.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ConsentCustomerRepository extends JpaRepository<ConsentCustomer, Long> {
      ConsentCustomer findConsentCustomerByIdentifierTypeAndIdentifier(IdentifierType identifierType, String identifier);
}
