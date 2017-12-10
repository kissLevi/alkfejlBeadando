package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment,Long> {
    

}
