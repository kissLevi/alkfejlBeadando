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
    @Id
    @Column(name = "AD_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Date deadline;

    @JoinColumn(name = "COSTUMER_ID")
    @ManyToOne(targetEntity = User.class)
    private User costumer_id;

    @JoinColumn(name = "DELIVER_ID")
    @ManyToOne(targetEntity = User.class)
    private User deliver_id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public enum Status{
        PENDING,ACCEPTED,DELETED
    }

    @OneToOne(fetch=FetchType.LAZY, mappedBy="ad_id")
    private Payment payment;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="ad_id")
    private Rating rating;
}
