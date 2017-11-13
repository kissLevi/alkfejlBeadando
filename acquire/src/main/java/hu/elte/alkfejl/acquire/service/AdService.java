package hu.elte.alkfejl.acquire.service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewAd;
import hu.elte.alkfejl.acquire.model.post.NewRating;
import hu.elte.alkfejl.acquire.repository.AdvertisementRepository;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AdService {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    
    @Autowired
    private RatingRepository ratings;
    
    @Autowired
    private UserRepository users;

    public void delete(int id){
        advertisementRepository.delete(new Long(id));
    }

    public Ad update(int id, NewAd ad){
        Ad currAd = advertisementRepository.findOne(new Long(id));
        currAd.clone(ad);
        return advertisementRepository.save(currAd);
    }
    
    public boolean complete(Long id, Long userID){
        try{
            Ad currentAd = advertisementRepository.findOne(id);
            if(currentAd.getCostumer_id().equals(userID) && currentAd.getStatus().equals(Ad.Status.ACCEPTED)){
                advertisementRepository.delete(currentAd);
                User customer = users.findOne(currentAd.getCostumer_id());
                User deliver = users.findOne(currentAd.getDeliver_id());
                customer.getPendigRatings().add(new Rating(deliver, customer));
                deliver.setBalance(deliver.getBalance()+currentAd.getPrice());
                users.save(customer);
                users.save(deliver);
                return true;
            }
            else{
                return false;
            }
        }catch(NullPointerException ex){
            return false;
        }
        
        
    }
}
