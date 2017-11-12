package hu.elte.alkfejl.acquire.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.post.NewAd;
import hu.elte.alkfejl.acquire.repository.AdvertisementRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AdService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public void delete(int id){
        advertisementRepository.delete(new Long(id));
    }

    public Ad update(int id, NewAd ad){
        Ad currAd = advertisementRepository.findOne(new Long(id));
        currAd.clone(ad);
        return advertisementRepository.save(currAd);
    }
}
