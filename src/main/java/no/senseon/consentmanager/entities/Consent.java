package entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "consent")
@EntityListeners(ConsentEntityListener.class)
public class Consent extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Boolean consentIndicator = false;
    @ManyToOne(optional = false)
    @JoinColumn(name = "consent_types_id")
    private ConsentTypes consentTypes;
    @ManyToOne(optional = false)
    @JoinColumn(name = "consent_customer_id")
    private ConsentCustomer consentCustomer;
}
