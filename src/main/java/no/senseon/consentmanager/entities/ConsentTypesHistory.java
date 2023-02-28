package entities;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@Entity
@Table(name = "consent_types_history")
@EntityListeners(AuditingEntityListener.class)
public class ConsentTypesHistory {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "consent_types_id")
    private ConsentTypes consentTypes;
    @Column(length = 1000)
    private String consentTypesContents;
    @CreatedBy
    @Column(length = 30)
    private String modifiedBy;
    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;
    @Column(length = 30)
    @Enumerated(STRING)
    private Action action;

    public ConsentTypesHistory(){
    }

    public ConsentTypesHistory(ConsentTypes consentTypes, Action action){
        this.consentTypes = consentTypes;
        this.consentTypesContents = consentTypes.toString();
        this.action = action;
    }
}
