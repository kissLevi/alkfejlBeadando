package hu.elte.alkfejl.acquire.model.post;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewAd {
    private String description;

    private String location;

    private int price;

    private Date deadline;
}
