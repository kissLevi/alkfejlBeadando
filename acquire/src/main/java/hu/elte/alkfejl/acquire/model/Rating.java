package hu.elte.alkfejl.acquire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 
import javax.persistence.*;
 
@Entity
@Table(name = "RATING")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends BaseEntity{
    public Rating(User rated,User user){
        this.user = user;
        this.rated = rated;
    }
    
    
    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="PENDING_RATINGS",
    joinColumns={@JoinColumn(name="rate_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private User user;
    

    @Column(nullable = false)
    private float rating;

    @Column
    private String description;
    
   
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="Rated_id", nullable=false)
    private User rated;
    
    public String getRated(){
        return rated.getUsername();
    }
    public User getRatedUser(){
        return rated;
    }
    

}