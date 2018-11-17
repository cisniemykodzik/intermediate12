package pl.sda.intermediate12.categories;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Category {
    private Integer id;
    private Integer parentId;
    private Integer depth;
    private String name;
}
