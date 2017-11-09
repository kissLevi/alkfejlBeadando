package hu.elte.alkfejl.acquire.model;

import hu.elte.alkfejl.acquire.model.post.NewAd;
import java.text.SimpleDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ADS") //Nem engedi order-nek nevezni, nem jelenik meg a tábla, mert sql keyword
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Ad extends BaseEntity {
   
    
    public Ad(NewAd ad,User costumer){
        description = ad.getDescription();
        location = ad.getLocation();
        price = ad.getPrice();
        deadline = ad.getDeadline();
        costumer_id = costumer;
        deliver_id = costumer;
        status = status.PENDING;
    }
    
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Date deadline;

    @JoinColumn(name = "COSTUMER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User costumer_id;
    
    

    @JoinColumn(name = "DELIVER_ID")
    @ManyToOne
    private User deliver_id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public enum Status{
        PENDING,ACCEPTED,DELETED
    }

//    @OneToOne(fetch=FetchType.LAZY, mappedBy="ad_id")
//    private Payment payment;
//

//    @OneToOne(fetch=FetchType.LAZY, mappedBy="ad_id")
//    private Rating rating_id;
//    
//    public Long getRating_id(){
//        return rating_id.getId();
//    }
    
    public String getDeadline(){
        SimpleDateFormat ft = 
                new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss ");
        return ft.format(deadline);
    }
//    
    public Long getCostumer_id(){
        return costumer_id.getId();
    }
    public Long getDeliver_id(){
        return deliver_id.getId();
    }
}
