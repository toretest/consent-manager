package entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consent_channel",uniqueConstraints = {
        @UniqueConstraint(name = "CONSTRAINT_consent_channel_unique_name", columnNames = {"name"}),
})
public class ConsentChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 20) //unique = true,
    private String name;
}
