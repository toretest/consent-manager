package no.ya.topaz.consentmanager.repositories;

import no.ya.topaz.consentmanager.entity.ConsentCustomer;
import no.ya.topaz.consentmanager.entity.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ConsentCustomerRepository extends JpaRepository<ConsentCustomer, Long> {
      ConsentCustomer findConsentCustomerByIdentifierTypeAndIdentifier(IdentifierType identifierType,String identifier);
}