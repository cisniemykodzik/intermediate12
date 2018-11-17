package pl.sda.intermediate12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate12.categories.Category;
import pl.sda.intermediate12.categories.InMemoryCategoryDAO;

import java.util.List;

public class CategoryParseTest {
    @Test
    public void checkParseOK() {
        Category novel1 = Category.builder().depth(1).id(2).parentId(1).name("Powieści").build();
        Category novel2 = Category.builder().depth(1).id(8).parentId(7).name("Powieści").build();
        Category classTwo = Category.builder().depth(2).id(6).parentId(4).name("Klasa druga").build();

        List<Category> categoryList = InMemoryCategoryDAO.getInstance().getCategoryList();

        Category novel1FromDAO = categoryList.stream()
                .filter(o -> o.getId().equals(2))
                .findFirst()
                .orElse(null);
        Category novel2FromDAO = categoryList.stream()
                .filter(o -> o.getId().equals(8))
                .findFirst()
                .orElse(null);
        Category classTwoFromDAO = categoryList.stream()
                .filter(o -> o.getId().equals(6))
                .findFirst()
                .orElse(null);

        Assertions.assertEquals(novel1.getParentId(), novel1FromDAO.getParentId());
        Assertions.assertEquals(novel2.getParentId(), novel2FromDAO.getParentId());
        Assertions.assertEquals(classTwo.getParentId(), classTwoFromDAO.getParentId());

    }
}
