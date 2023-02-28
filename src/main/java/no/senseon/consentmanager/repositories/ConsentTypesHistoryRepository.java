package no.ya.topaz.consentmanager.repositories;

import no.ya.topaz.consentmanager.entity.ConsentTypes;
import no.ya.topaz.consentmanager.entity.ConsentTypesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsentTypesHistoryRepository extends JpaRepository<ConsentTypesHistory, Long> {
    List<ConsentTypesHistory> findConsentTypesHistoriesByConsentTypes(ConsentTypes consentTypes);
}
