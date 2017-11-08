package hu.elte.alkfejl.acquire.model;

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

    @OneToOne(fetch=FetchType.LAZY, mappedBy="ad_id")
    private Rating rating_id;
    
    public Long getRating_id(){
        return rating_id.getId();
    }
    
    public Long getCostumer_id(){
        return costumer_id.getId();
    }
    public Long getDeliver_id(){
        return deliver_id.getId();
    }
}
