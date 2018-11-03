package pl.sda.intermediate12;

public class OnlyOneControler {
    private CategoryService categoryService = new CategoryService();


    public String categories(String searchText) {
        categoryService.filterCategories(searchText);
        return "categories";
    }
}
