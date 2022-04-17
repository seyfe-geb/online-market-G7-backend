package net.groupseven.onlinemarketg7backend.payment_method.repository;


import net.groupseven.onlinemarketg7backend.payment_method.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
