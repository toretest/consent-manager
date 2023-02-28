package entities;

import no.ya.topaz.consentmanager.springcomponents.BeanUtil;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static no.ya.topaz.consentmanager.entity.Action.DELETED;
import static no.ya.topaz.consentmanager.entity.Action.INSERTED;
import static no.ya.topaz.consentmanager.entity.Action.UPDATED;

public class ConsentTypesEntityListener {

    @PrePersist
    public void prePersist(ConsentTypes target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(ConsentTypes target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(ConsentTypes target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    public void perform(ConsentTypes target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new ConsentTypesHistory(target, action));

    }
}
