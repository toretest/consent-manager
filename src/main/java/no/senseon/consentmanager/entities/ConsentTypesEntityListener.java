package no.senseon.consentmanager.entities;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import static no.senseon.consentmanager.entities.Action.DELETED;
import static no.senseon.consentmanager.entities.Action.INSERTED;


public class ConsentTypesEntityListener {

    @PrePersist
    public void prePersist(ConsentTypes target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(ConsentTypes target) {
      //  perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(ConsentTypes target) {
        perform(target, DELETED);
    }

   // @Transactional(MANDATORY)
    public void perform(ConsentTypes target, Action action) {
      /*  EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new ConsentTypesHistory(target, action));*/

    }
}
