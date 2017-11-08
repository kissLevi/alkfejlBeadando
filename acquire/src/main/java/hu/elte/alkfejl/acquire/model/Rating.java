package hu.elte.alkfejl.acquire.model;

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
    @JoinColumn(name = "RATER_ID")
    @ManyToOne(optional = false)
    private User rater_id;

    @JoinColumn(name = "RATED_ID")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User rated_id;

    @OneToOne(fetch=FetchType.LAZY,optional = false)
    private Ad ad_id;
    
    
    @Column(nullable = false)
    private int rating;

    @Column
    private String description;
    
    public User getRated_id(){
        return null;
    }
}