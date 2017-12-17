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
@Table(name = "ADS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Ad extends BaseEntity {
   
    
    public Ad(NewAd ad,User costumer){
        name = ad.getName();
        description = ad.getDescription();
        location = ad.getLocation();
        price = ad.getPrice();
        deadline = ad.getDeadline();
        costumer_id = costumer;
        deliver_id = costumer;
        status = status.PENDING;
    }
    
    @Column(nullable = false)
    private String name;
    
    
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Date deadline;

    
    @JoinColumn(name = "COSTUMER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User costumer_id;
    
    
    @JoinColumn(name = "DELIVER_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private User deliver_id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public enum Status{
        PENDING,ACCEPTED,DONE,UNRATED
    }

    public void clone(NewAd ad){
        this.description=ad.getDescription();
        this.location=ad.getLocation();
        this.deadline=ad.getDeadline();
        this.price=ad.getPrice();
    }
    
//    public String getDeadline(){
//        SimpleDateFormat ft = 
//                new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss ");
//        return ft.format(deadline);
//    }
//    
//    public Long getCostumer_id(){
//        return costumer_id.getId();
//    }
//    public Long getDeliver_id(){
//        return deliver_id.getId();
//    }
}
