package pl.sda.intermediate12;

import java.util.Map;

public class OnlyOneController {
    private CategoryService categoryService = new CategoryService();
    private UserRegistrationService userRegistrationService = new UserRegistrationService();
    private UserValidationService userValidationService = new UserValidationService();

    public String categories(String searchText) {
        categoryService.filterCategories(searchText);
        return "categories";
    }

    public String registerEffect(UserRegistrationDTO userRegistrationDTO) {
        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        if(errorsMap.isEmpty()){
            try {
                userRegistrationService.registerUser(userRegistrationDTO);
            } catch (UserExistsException e) {
               //FIXME
                return "registerForm";
            }
        }
        else {
            //Fixme
            return "registerForm";
        }
        return "registerEffect";
    }
}
