package net.groupseven.onlinemarketg7backend.payment_method.service;

import com.online.market.payment_method.dto.PaymentMethodDto;
import com.online.market.payment_method.dto.SavePaymentMethodDto;

import java.util.List;


public interface PaymentMethodService {
    List<PaymentMethodDto> findAll();
    PaymentMethodDto findById(long id);
    PaymentMethodDto add(SavePaymentMethodDto dto);
    PaymentMethodDto updateById(long id, SavePaymentMethodDto dto);
    void deleteById(long id);
}
