package no.ya.topaz.consentmanager.repositories;

import no.ya.topaz.consentmanager.entity.ConsentChannel;
import no.ya.topaz.consentmanager.entity.ConsentCustomer;
import no.ya.topaz.consentmanager.entity.ConsentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
@SuppressWarnings("squid:S00100")
public interface ConsentHistoryRepository extends JpaRepository<ConsentHistory, Long> {
    List<ConsentHistory> findConsentHistoriesByConsent_ConsentTypes_ConsentChannelAndConsent_ConsentCustomer(ConsentChannel consentChannel, ConsentCustomer consentCustomer);
}
