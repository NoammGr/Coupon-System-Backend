package app.core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import app.core.connectionsystem.ClientType;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ClientType clientType = ClientType.COMPANY;
}
