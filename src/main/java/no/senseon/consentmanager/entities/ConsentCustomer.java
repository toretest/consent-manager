package no.senseon.consentmanager.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consent_customer", uniqueConstraints = {
        @UniqueConstraint(name = "CONSTRAINT_consent_customer_unique_identifier", columnNames = {"identifier"}),
})
public class ConsentCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(length = 30)
    private String identifier;
    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private IdentifierType identifierType;
}
