package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.TemporalType.TIMESTAMP;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consent_history")
@EntityListeners(AuditingEntityListener.class)
public class ConsentHistory {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "consent_id")
    private Consent consent;
    @Column(length = 2000)
    private String consentContent;
    @Column(length = 30)
    @CreatedBy
    private String modifiedBy;
    @Column(length = 30)
    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;
    @Enumerated(STRING)
    @Column(length = 30)
    private Action action;

    public ConsentHistory(Consent consent, Action action) {
        this.consent = consent;
        this.consentContent = consent.toString();
        this.action = action;
    }
}
