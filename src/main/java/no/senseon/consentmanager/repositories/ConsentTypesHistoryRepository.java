package no.senseon.consentmanager.repositories;

import no.senseon.consentmanager.entities.ConsentTypes;
import no.senseon.consentmanager.entities.ConsentTypesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsentTypesHistoryRepository extends JpaRepository<ConsentTypesHistory, Long> {
    List<ConsentTypesHistory> findConsentTypesHistoriesByConsentTypes(ConsentTypes consentTypes);
}
