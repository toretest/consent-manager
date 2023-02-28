package no.senseon.consentmanager.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "consent_types")
@EntityListeners(ConsentTypesEntityListener.class)
public class ConsentTypes extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, length = 20)
    private String name;
    @Column(length = 50)
    private String displayName;
    @Column(nullable = false)
    private Boolean displayToCustomer;
    @Column(nullable = false)
    private Boolean consentDefaultIndicator;
    @Column(length = 300)
    private String consentText;
    @Column(length = 4000)
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "consent_channel_id")
    private ConsentChannel consentChannel;
}
