package hu.elte.alkfejl.acquire.model;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity {
    @JoinColumn(name = "PAYER_ID")
    @ManyToOne(targetEntity = User.class)
    private int payer_id;
    
    @JoinColumn(name = "RECIEVER_ID")
    @ManyToOne(targetEntity = User.class)
    private int reciever_id;
 
    @OneToOne(fetch=FetchType.LAZY)
 
    @JoinColumn(name="AD_ID")
    private Ad ad_id;
 
    @Column(nullable = false)
    private int amount;
 }