package hu.elte.alkfejl.acquire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {
    @Column
    private int costumer_id;

    @Column
    private int deliver_id;

    @Column
    private int product_id;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    private enum Status{
        PENDING, ACCEPTED, DELETED
    }
}
