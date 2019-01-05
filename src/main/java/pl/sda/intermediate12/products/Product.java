package pl.sda.intermediate12.products;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Product {
    private int id;
    private Author author;
    private String title;
    private Genre genre;
    private BigDecimal price;
    private Date publish_date;
    private String description;
}
