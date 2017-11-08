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
    @ManyToOne(targetEntity = User.class,optional = false)
    private int rater_id;

    @JoinColumn(name = "RATED_ID")
    @ManyToOne(targetEntity = User.class,optional = false)
    private int rated_id;

    @OneToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name="AD_ID")
    private Ad ad_id;

    @Column(nullable = false)
    private int rating;

    @Column
    private String description;
}