package hu.elte.alkfejl.acquire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 
import javax.persistence.*;
 
@Entity
@Table(name = "RATINGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends BaseEntity{
    public Rating(User rater,User rated,RateingType type){
        this.rater = rater;
        this.rated = rated;
        this.type = type;
    }
    
    
    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    private User rater;
    

    @Column(nullable = false)
    private float rating;

    @Column
    private String description;
   
   
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="Rated_id", nullable=false)
    private User rated;
    
    @Enumerated(EnumType.STRING)
    @Column
    private RateingType type;

    public enum RateingType{
        DELIVER,CUSTOMER
    }
    
    public String getRated(){
        return rated.getUsername();
    }
    public User getRatedUser(){
        return rated;
    }
    

}