package pl.sda.intermediate12;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryService {
    private InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();

    public List<CategoryDTO> filterCategories(String searchText) {
        List<Category> categoryList = inMemoryCategoryDAO.getCategoryList();
        Map<Integer,CategoryDTO> dtoMap = categoryList.stream()
                .map(c -> buildCategoryDTO(c))
                .collect(Collectors.toMap(k->k.getId(),v->v));

        return dtoMap.values().stream()
                .peek(dto -> dto.setParentCat(dtoMap.get(dto.getParentId())))
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
