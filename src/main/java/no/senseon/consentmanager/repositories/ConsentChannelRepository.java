package no.senseon.consentmanager.repositories;


import no.senseon.consentmanager.entities.ConsentChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ConsentChannelRepository extends JpaRepository<ConsentChannel, Long> {
    ConsentChannel findConsentChannelByName(String name);
}
