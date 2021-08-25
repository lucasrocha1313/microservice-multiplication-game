package br.com.tonim.multiplication.challenge.domain;

import br.com.tonim.multiplication.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue
    private Long id;
    private Integer factorA;
    private Integer factorB;
    private Integer resultAttempt;
    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
