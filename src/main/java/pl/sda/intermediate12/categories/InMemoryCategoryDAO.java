package pl.sda.intermediate12.categories;


import com.google.common.io.Resources;
import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//singleton by hand
public class InMemoryCategoryDAO {
    private static InMemoryCategoryDAO instance;
    @Getter
    private List<Category> categoryList;

    private InMemoryCategoryDAO() {
        categoryList = initializeCategories();
    }

    private List<Category> initializeCategories() {
        try {
            List<String> strings = Resources.readLines(Resources.getResource("kategorie2.txt"), Charset.forName("UNICODE"));
            List<Category> categories = new ArrayList<>();

            int counter = 1;
            for (String line : strings) {
                categories.add(Category.builder()
                        .name(line.trim())
                        .id(counter++)
                        .depth(calculateDepth(line))
                        .build());
            }

            Map<Integer, List<Category>> categoryMap = new HashMap<>();
            for (Category category : categories) {
                if (categoryMap.containsKey(category.getDepth())) {
                    categoryMap.get(category.getDepth()).add(category);
                } else {
                    List<Category> cats = new ArrayList<>();
                    cats.add(category);
                    categoryMap.put(category.getDepth(), cats);
                }
            }
            populateParentID(categoryMap, 0);
            return categories;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void populateParentID(Map<Integer, List<Category>> categoryMap, Integer depth) {
        if(!categoryMap.containsKey(depth)){
            return;
        }
        List<Category> children = categoryMap.get(depth);
        for (Category child : children) {
            if (depth != 0) {
                List<Category> potentialParents = categoryMap.get(depth - 1);
                int parentID = 0;
                for (Category potentialParent : potentialParents) {
                    if (potentialParent.getId() < child.getId() && parentID < potentialParent.getId()) {
                        parentID = potentialParent.getId();
                    }
                }
                if(parentID == 0){
                    throw new RuntimeException();
                }
                child.setParentId(parentID);
            }
        }
        populateParentID(categoryMap, ++depth);
    }


    private int calculateDepth(String line) {
        if(line.startsWith(" ")){
            return line.split("\\S")[0].length();
        }
        return 0;
    }

    public static InMemoryCategoryDAO getInstance() {
        if (instance == null) {
            synchronized (InMemoryCategoryDAO.class) {
                if (instance == null) {
                    instance = new InMemoryCategoryDAO();
                }
            }
        }
        return instance;
    }


}
