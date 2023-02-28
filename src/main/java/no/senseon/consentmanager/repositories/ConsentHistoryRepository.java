package no.senseon.consentmanager.repositories;

import no.senseon.consentmanager.entities.ConsentChannel;
import no.senseon.consentmanager.entities.ConsentCustomer;
import no.senseon.consentmanager.entities.ConsentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ConsentHistoryRepository extends JpaRepository<ConsentHistory, Long> {
    List<ConsentHistory> findConsentHistoriesByConsent_ConsentTypes_ConsentChannelAndConsent_ConsentCustomer(ConsentChannel consentChannel, ConsentCustomer consentCustomer);
}
