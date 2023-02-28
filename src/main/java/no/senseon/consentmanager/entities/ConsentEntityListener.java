package no.senseon.consentmanager.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.transaction.Transactional;

import static jakarta.transaction.Transactional.TxType.MANDATORY;
import static no.senseon.consentmanager.entities.Action.*;
import static no.senseon.consentmanager.springcomponents.BeanUtil.getBean;

public class ConsentEntityListener {
    @PrePersist
    public void prePersist(Consent target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(Consent target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Consent target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    void perform(Consent target, Action action) {
        EntityManager entityManager = getBean(EntityManager.class);
        entityManager.persist(new ConsentHistory(target, action));

    }
}
