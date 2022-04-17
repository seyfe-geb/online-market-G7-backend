package net.groupseven.onlinemarketg7backend.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.groupseven.onlinemarketg7backend.address.model.Address;
import net.groupseven.onlinemarketg7backend.payment_method.model.PaymentMethod;
import net.groupseven.onlinemarketg7backend.role.model.Role;
import net.groupseven.onlinemarketg7backend.user.dto.UnApprovedSellerDto;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User implements Serializable { //UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    private boolean enabled = true;

    @Column(name = "is_approved_seller")
    private boolean isApprovedSeller = false;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<PaymentMethod> paymentMethods;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}))
    private Set<Role> authorities = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return enabled;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return enabled;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return enabled;
//    }
}
