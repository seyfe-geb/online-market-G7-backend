package net.groupseven.onlinemarketg7backend.reward.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.groupseven.onlinemarketg7backend.order.model.Order;
import net.groupseven.onlinemarketg7backend.user.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rewards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reward implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int points;
}
