package entities;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static entities.Action.*;

import static no.ya.topaz.consentmanager.springcomponents.BeanUtil.getBean;

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
