package net.groupseven.onlinemarketg7backend.payment_method.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.address.model.Address;
import net.groupseven.onlinemarketg7backend.user.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="payment_methods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private PaymentMethodType type;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private int csv;

    @Column(name ="is_default")
    private boolean isDefault;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_address.id", nullable = false)
    @JsonBackReference
    private Address billingAddress;
}
