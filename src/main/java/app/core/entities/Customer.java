package app.core.entities;

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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "customer_coupon", joinColumns = {@JoinColumn(name = "customer_id")}, inverseJoinColumns = {@JoinColumn(name = "coupon_id")})
    private List<Coupon> coupons;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ClientType clientType = ClientType.CUSTOMER;
}
