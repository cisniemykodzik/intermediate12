package pl.sda.intermediate12;

import org.junit.jupiter.api.Test;

public class CategoryParseTest {
    @Test
    public void checkParseOK(){
        Category novel1 = Category.builder()
                .depth(1)
                .id(2)
                .parentId(1)
                .name("Powieści")
                .build();
        Category novel2 = Category.builder()
                .depth(1)
                .id(8)
                .parentId(7)
                .name("Powieści")
                .build();
        Category classTwo = Category.builder()
                .depth(2)
                .id(6)
                .parentId(4)
                .name("Klasa druga")
                .build();
    }
}
