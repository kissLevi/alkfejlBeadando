package hu.elte.alkfejl.acquire.controller;

import hu.elte.alkfejl.acquire.annotation.Role;
import hu.elte.alkfejl.acquire.model.Ad;
import hu.elte.alkfejl.acquire.model.post.NewAd;
import hu.elte.alkfejl.acquire.model.User;
import hu.elte.alkfejl.acquire.service.AdService;
import hu.elte.alkfejl.acquire.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//GET  /ads       List all uncompleted ads
//POST /ads       Creating new ad request NewAd typed ad in requestbody
//
//GET /ads/id     List some additional information about ad 
//PUT /ads/id     Update ad, request NewAd Typed ad in requestBody
//DELETE /ads/id  Delete ad

//PUT ads/id/accept     Make an ad accepted if a deliver accepts it
//PUT ads/id/complete   Remove the ad from database and making pendingratings for the customer and the deliver

@RestController
@RequestMapping("/ads")
public class AdvertisementController {
    
    @Autowired
    private SessionService sessionService;

    @Autowired
    private AdService adService;
    
    @GetMapping
    public ResponseEntity<Iterable<Ad>> getAvailableAds(){
        return ResponseEntity.ok(adService.getAllAds());
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @PostMapping
    public ResponseEntity addAdvertisement(@RequestBody NewAd ad)
    {
        if(adService.newAd(sessionService.getCurrentUser().getId(), ad)){
            return ResponseEntity.ok().build();  
        }   
        return ResponseEntity.badRequest().build();
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @GetMapping("/{adId}")
    public ResponseEntity<Ad> getAd(@PathVariable int adId)
    {
        Ad ad = adService.getAd(adId);
        if(ad == null){
            return ResponseEntity.badRequest().build();
        }
        else{
           return ResponseEntity.ok(ad);
        } 
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("/{adID}")
    private ResponseEntity updateAd(@PathVariable int adID, @RequestBody NewAd ad){
        Ad updated = adService.updateAd(adID,ad);
        return ResponseEntity.ok(updated);
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @DeleteMapping("/{adID}")
    private ResponseEntity deleteAd(@PathVariable int adID){
        if(adService.deleteAd(adID,sessionService.getCurrentUser().getId())){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();    
    }
    
    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("{adId}/accept")
    public ResponseEntity acceptAdvertisement(@PathVariable int adId){
        if(adService.acceptAd(sessionService.getCurrentUser().getId(), adId)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({User.Role.USER, User.Role.ADMIN})
    @PutMapping("{adId}/complete")
    public ResponseEntity doneAdvertisement(@PathVariable int adId){
        if(adService.completeAd(new Long(adId), sessionService.getCurrentUser().getId()))
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();   
    }
}
