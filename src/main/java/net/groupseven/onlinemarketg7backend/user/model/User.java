package net.groupseven.onlinemarketg7backend.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.online.market.address.model.Address;
import com.online.market.payment_method.model.PaymentMethod;
import com.online.market.role.model.Role;
import com.online.market.user.dto.UnApprovedSellerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedNativeQuery(name = "findSellersByApproved",
        query  = "SELECT u.id, u.fname, u.lname, u.email, u.created_at, u.modified_at"
                + " FROM users u INNER JOIN user_roles ur ON  ur.user_id = u.id"
                + " INNER JOIN roles r ON ur.role_id = r.id"
                + " WHERE u.is_approved_seller = ?1 and r.authority = '" + Role.SELLER + "'",
        resultSetMapping  = "mapping_unapproved_seller_dto")
@SqlResultSetMapping(name = "mapping_unapproved_seller_dto",
        classes = @ConstructorResult(targetClass = UnApprovedSellerDto.class,
                columns = {@ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "fname", type = String.class),
                        @ColumnResult(name = "lname", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "created_at", type = Date.class),
                        @ColumnResult(name = "modified_at", type = Date.class)}))

public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    private String fname;
    @NotNull
    private String lname;

    private boolean enabled = true;

    @Column(name = "is_approved_seller")
    private boolean isApprovedSeller = false;

    @CreatedDate()
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

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

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
