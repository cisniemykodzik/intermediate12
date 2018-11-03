package pl.sda.intermediate12;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();

    public List<CategoryDTO> filterCategories(String searchText) {
        List<Category> categoryList = inMemoryCategoryDAO.getCategoryList();
        return categoryList.stream()
                .map(c -> buildCategoryDTO(c))
                .peek(dto -> dto.setParentCat(findParent(dto.getParentId())))
                .map(dto -> populateStateAndOpenParents(dto, searchText.trim()))
                .collect(Collectors.toList());

    }

    private CategoryDTO populateStateAndOpenParents(CategoryDTO dto, String searchText) {
        if (dto.getName().equals(searchText)) {
            dto.getCategoryState().setOpen(true);
            dto.getCategoryState().setSelected(true);
            openParent(dto);
        }
        return dto;
    }

    private void openParent(CategoryDTO child) {
        CategoryDTO parentCat = child.getParentCat();
        if (parentCat == null) {
            return;
        }
        parentCat.getCategoryState().setOpen(true);
        openParent(parentCat);
    }

    private CategoryDTO findParent(Integer parentId) {
        List<Category> categoryList = inMemoryCategoryDAO.getCategoryList();
        return categoryList.stream()
                .filter(f -> f.getId().equals(parentId))
                .findFirst()
                .map(m -> buildCategoryDTO(m))
                .orElse(null);


    }

    private CategoryDTO buildCategoryDTO(Category c) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(c.getId());
        categoryDTO.setParentId(c.getParentId());
        categoryDTO.setName(c.getName());
        categoryDTO.setDepth(c.getDepth());
        categoryDTO.setCategoryState(new CategoryState());
        return categoryDTO;
    }
}
