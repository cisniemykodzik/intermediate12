package pl.sda.intermediate12;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {
    private Integer id;
    private Integer parentId;
    private Integer depth;
    private String name;
    private CategoryDTO parentCat;
    private CategoryState categoryState;
}
