package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.post.NewAd;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.repository.AdvertisementRepository;
import hu.elte.alkfejl.acquire.repository.UserRepository;
import hu.elte.alkfejl.acquire.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ads")
public class AdvertisementController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AdvertisementRepository adverisemets;
    
    @Autowired
    private SessionService sessionService;
    
    @GetMapping
    public ResponseEntity<Iterable<Ad>> getAviliableAds(){
        return ResponseEntity.ok(adverisemets.findAll());
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
            
            adverisemets.save(newAd);
            userRepository.save(currentUser);
            return ResponseEntity.ok().build();    
        }   
     
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @GetMapping("/{adId}")
    public ResponseEntity<Ad> getAd(@PathVariable int adId)
    {
        Ad ad = adverisemets.findOne(new Long(adId));
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
        Ad ad = adverisemets.findOne(new Long(adId));
        if(ad == null){
            return ResponseEntity.badRequest().build();
        }
        else{
           ad.setDeliver_id(sessionService.getCurrentUser());
           ad.setStatus(Ad.Status.ACCEPTED);
           adverisemets.save(ad);
           return ResponseEntity.ok().build();
        }        
    }
    
    //@Role({User.Role.USER, User.Role.ADMIN})
//    @PutMapping("{adId}/done")
//    public ResponseEntity doneAdvertisement(@PathVariable int adId){
//        Ad ad = adverisemets.findOne(new Long(adId));
//        if(ad == null){
//            return ResponseEntity.badRequest().build();
//        }
//        else{
//           User deliver = ad.getDeliver();
//           deliver.setBalance(deliver.getBalance()+ad.getPrice());
//           ad.setStatus(Ad.Status.UNRATED);
//           adverisemets.save(ad);
//           userRepository.save(deliver);
//           return ResponseEntity.ok().build();
//        }        
//    }
}
