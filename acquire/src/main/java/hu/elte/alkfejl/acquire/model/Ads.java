package hu.elte.alkfejl.acquire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Ads") //Nem engedi order-nek nevezni, nem jelenik meg a tábla, mert sql keyword
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Ads extends BaseEntity {
    @Column
    private String description;

    @Column
    private String location;

    @Column
    private int price;

    @Column
    private Date deadline;
}
