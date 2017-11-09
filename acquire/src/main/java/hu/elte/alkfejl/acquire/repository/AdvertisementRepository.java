package hu.elte.alkfejl.acquire.repository;

import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.Ad.Status;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface AdvertisementRepository extends CrudRepository<Ad,Long>{
    //@Query("select a from Ad a u where a.customer_id = ?%1 ")
    //List<Ad> getAdvertisementsWithStatus(Status status);
}
