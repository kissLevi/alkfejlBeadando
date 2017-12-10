package hu.elte.alkfejl.acquire.service;

import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.Payment;
import hu.elte.alkfejl.acquire.model.Rating;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.model.post.NewAd;
import hu.elte.alkfejl.acquire.repository.AdvertisementRepository;
import hu.elte.alkfejl.acquire.repository.PaymentRepository;
import hu.elte.alkfejl.acquire.repository.RatingRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import lombok.Data;
import org.hibernate.jpa.criteria.expression.function.CurrentTimestampFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@Data
public class AdService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private RatingRepository ratings;
    
    @Autowired
    private UserRepository userRepository;
    
    public Ad getAd(int adId){
        return advertisementRepository.findOne(new Long(adId));
    }
    
    public Iterable<Ad> getAllAds(){
        return advertisementRepository.findAll();
    }
    
    
    public Ad newAd(Long userId,NewAd ad){
        User ownerOfAdd = userRepository.findOne(userId);
        if(ownerOfAdd.getBalance()>=ad.getPrice()){
            Ad newAd = new Ad(ad,ownerOfAdd);
            ownerOfAdd.addBalance(-newAd.getPrice());
            
            advertisementRepository.save(newAd);
            userRepository.save(ownerOfAdd);
            
            return newAd;
        }
        return null;
    }
    
    
    public boolean deleteAd(int id,Long userId){
        Ad addToDelete = advertisementRepository.findOne(new Long(id));
   
        if(addToDelete.getCostumer_id().equals(userId)){
            User adOwner = userRepository.findOne(userId);
            adOwner.addBalance(addToDelete.getPrice());
            advertisementRepository.delete(new Long(id));
            return true;
        }
        return false;
       
    }

    public Ad updateAd(int id, NewAd ad){
        Ad currAd = advertisementRepository.findOne(new Long(id));
        currAd.clone(ad);
        return advertisementRepository.save(currAd);
    }
    
    
    public boolean acceptAd(Long userId,int adId){
        Ad ad = advertisementRepository.findOne(new Long(adId));
        if(ad == null || ad.getStatus().equals(Ad.Status.ACCEPTED) || ad.getCostumer_id().equals(userId)){
            return false;
        }
        else{
           ad.setDeliver_id(userRepository.findOne(userId));
           ad.setStatus(Ad.Status.ACCEPTED);
           advertisementRepository.save(ad);
           return true;
        }
    }
    
    public boolean failAd(Long id, Long userID){
        try{
            Ad currentAd = advertisementRepository.findOne(id);
            if(currentAd.getCostumer_id().equals(userID) && currentAd.getStatus().equals(Ad.Status.ACCEPTED)){
                advertisementRepository.delete(currentAd);
                
                User customer = userRepository.findOne(currentAd.getCostumer_id());
                User deliver = userRepository.findOne(currentAd.getDeliver_id());

                int amount = currentAd.getPrice();
                
                Rating newDeliverRating = new Rating(customer, deliver, Rating.RatingType.DELIVER);
                newDeliverRating.setRating(1);
                newDeliverRating.setDescription("Nem ért célba a megadott időn belül");
                
                customer.addBalance(currentAd.getPrice());
                deliver.setRating(RatingService.calculateNewRating(deliver, 1, ratings));
                System.out.println(deliver.getPendigRatings());
                System.out.println(customer.getPendigRatings());
                ratings.save(newDeliverRating);
                userRepository.save(customer);
                userRepository.save(deliver);
                return true;
            }
            else{
                return false;
            }
        }catch(NullPointerException ex){
            return false;
        }   
        
    }
    
    public boolean completeAd(Long id, Long userID){
        try{
            Ad currentAd = advertisementRepository.findOne(id);
            if(currentAd.getCostumer_id().equals(userID) && currentAd.getStatus().equals(Ad.Status.ACCEPTED)){
                advertisementRepository.delete(currentAd);
                
                User customer = userRepository.findOne(currentAd.getCostumer_id());
                User deliver = userRepository.findOne(currentAd.getDeliver_id());

                int amount = currentAd.getPrice();
                Payment payment = new Payment(customer,deliver,amount, new Timestamp(System.currentTimeMillis()));
                
                Rating newDeliverRating = new Rating(customer, deliver, Rating.RatingType.DELIVER);
                Rating newCustomerRating = new Rating(deliver, customer, Rating.RatingType.CUSTOMER);
                customer.getPendigRatings().add(newDeliverRating);
                deliver.getPendigRatings().add(newCustomerRating);
                
                deliver.addBalance(currentAd.getPrice());
                
                userRepository.save(customer);
                userRepository.save(deliver);
                paymentRepository.save(payment);
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
