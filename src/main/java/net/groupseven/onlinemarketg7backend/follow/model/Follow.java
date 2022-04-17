package net.groupseven.onlinemarketg7backend.follow.model;

import com.online.market.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "follows", uniqueConstraints={@UniqueConstraint(columnNames = {"follower_id", "followee_id"})})
@Entity
public class Follow {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followee_id", nullable = false)
    private User followee;
}
