package net.groupseven.onlinemarketg7backend.payment_method.controller;



//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.payment_method.dto.PaymentMethodDto;
import net.groupseven.onlinemarketg7backend.payment_method.dto.SavePaymentMethodDto;
import net.groupseven.onlinemarketg7backend.payment_method.service.PaymentMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Payment Methods")
@RestController
@RequestMapping(path = "/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService service;

    @GetMapping()
    public List<PaymentMethodDto> getAll() {return service.findAll();}

    @GetMapping("/{id}")
    public PaymentMethodDto get(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping
    public PaymentMethodDto add(@RequestBody SavePaymentMethodDto dto){
        return service.add(dto);
    }

    @PutMapping("/{id}")
    public PaymentMethodDto update(@PathVariable("id") long id, @RequestBody SavePaymentMethodDto dto){
        return service.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") long id){
        service.deleteById(id);
    }

}
