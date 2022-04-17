package net.groupseven.onlinemarketg7backend.follow.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.groupseven.onlinemarketg7backend.user.model.User;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "follows", uniqueConstraints={@UniqueConstraint(columnNames = {"follower_id", "followee_id"})})
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followee_id", nullable = false)
    private User followee;
}
