package pl.sda.intermediate12;

public class OnlyOneController {
    private CategoryService categoryService = new CategoryService();
    private UserRegistrationService userRegistrationService = new UserRegistrationService();

    public String categories(String searchText) {
        categoryService.filterCategories(searchText);
        return "categories";
    }

    public String registerEffect(UserRegistrationDTO userRegistrationDTO) {

        return "registerEffect";
    }
}
