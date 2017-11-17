package hu.elte.alkfejl.acquire.model;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PAYMENTS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends BaseEntity {
    @JoinColumn(name = "PAYER_ID")
    @ManyToOne(targetEntity = User.class,optional = false)
    private User payer;
    
    @JoinColumn(name = "RECIEVER_ID")
    @ManyToOne(targetEntity = User.class,optional = false)
    private User reciever;
 
    @Column(nullable = false)
    private int amount;

    @Column(name = "PAYMENT_TIME")
    private Timestamp ts;

    public Payment(User payer, User reciever, int amount, Timestamp ts){
        this.payer = payer;
        this.reciever = reciever;
        this.amount = amount;
        this.ts = ts;
    }
 }