package pl.sda.intermediate12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller//singleton by spring
public class OnlyOneController {
    @Autowired//DEPENDENCY INJECTION
    private CategoryService categoryService;
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private UserValidationService userValidationService;

    @RequestMapping(value = "/categories")
    public String categories(@RequestParam(required = false) String searchText, Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.filterCategories(searchText);
        model.addAttribute("catsdata", categoryDTOS); //To zostanie wyslane na front
        return "catspage"; //takiego htmla bedzie szukac nasza aplikacja
    }

    @RequestMapping(value = "/example")
    @ResponseBody
    public String example () {
        return "HELLO";
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
