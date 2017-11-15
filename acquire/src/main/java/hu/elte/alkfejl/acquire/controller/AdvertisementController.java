package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.post.NewAd;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.repository.AdvertisementRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.AdService;
import hu.elte.alkfejl.acquire.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
public class AdvertisementController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AdvertisementRepository advertisments;
    
    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdService adService;
    
    @GetMapping
    public ResponseEntity<Iterable<Ad>> getAvailableAds(){
        return ResponseEntity.ok(advertisments.findAll());
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @PostMapping("/add")
    public ResponseEntity addAdvertisement(@RequestBody NewAd ad)
    {
        User currentUser = sessionService.getCurrentUser();
        if(currentUser.getBalance()<ad.getPrice()){
            return ResponseEntity.badRequest().build();
        }
        else{
            currentUser.setBalance(currentUser.getBalance()-ad.getPrice());
            Ad newAd = new Ad(ad,currentUser);
            
            advertisments.save(newAd);
            userRepository.save(currentUser);
            return ResponseEntity.ok().build();    
        }   
     
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @GetMapping("/{adId}")
    public ResponseEntity<Ad> getAd(@PathVariable int adId)
    {
        Ad ad = advertisments.findOne(new Long(adId));
         if(ad == null){
            return ResponseEntity.badRequest().build();
        }
        else{
           return ResponseEntity.ok(ad);
        } 
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("{adId}/accept")
    public ResponseEntity acceptAdvertisement(@PathVariable int adId){
        Ad ad = advertisments.findOne(new Long(adId));
        if(ad == null || ad.getStatus().equals(Ad.Status.ACCEPTED) || ad.getCostumer_id().equals(sessionService.getCurrentUser())){
            return ResponseEntity.badRequest().build();
        }
        else{
           ad.setDeliver_id(sessionService.getCurrentUser());
           ad.setStatus(Ad.Status.ACCEPTED);
           advertisments.save(ad);
           return ResponseEntity.ok().build();
        }        
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @DeleteMapping("/{adID}")
    private ResponseEntity deleteAd(@PathVariable int adID){
        adService.delete(adID);
        return ResponseEntity.ok().build();
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("/{adID}")
    private ResponseEntity updateAd(@PathVariable int adID, @RequestBody NewAd ad){
        Ad updated = adService.update(adID,ad);
        return ResponseEntity.ok(updated);
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("{adId}/complete")
    public ResponseEntity doneAdvertisement(@PathVariable int adId){
        if(adService.complete(new Long(adId), sessionService.getCurrentUser().getId()))
        {
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
