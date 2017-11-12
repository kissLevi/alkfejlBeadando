package hu.elte.alkfejl.acquire.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewRating {
    private String description;
    private int rating;
}
