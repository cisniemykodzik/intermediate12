package pl.sda.intermediate12;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/")
    public String home (){
        return "index";
    }
    
    @RequestMapping(value = "/example")
    @ResponseBody
    public String example () {
        return "HELLO";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("form", new UserRegistrationDTO());
        model.addAttribute("countries", null); //todo zamiast nulla przekazanie kolekcji enumow
        return ""; //todo -> nazwa htmla
    }

    
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerEffect(UserRegistrationDTO userRegistrationDTO,Model model) {
        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        model.addAttribute("form", null); //todo tu zamiast nulla należy dodać dto z danymi usera ->
        model.addAttribute("countries", null); //todo zamiast nulla przekazanie kolekcji enumow
        if(errorsMap.isEmpty()){
            try {
                userRegistrationService.registerUser(userRegistrationDTO);
            } catch (UserExistsException e) {
                model.addAttribute("", null);//TODO -> tu zamiast nulla należy wpisać komunikat z exceptiona pod odpowiednią nazwą(nazwę znajdźcie w htmlu;) )
                return "registerForm";
            }
        }
        else {
            model.addAllAttributes(Maps.newHashMap()); //todo tu zamiast pustej mapy należy przekazać mapę z błędami
            return "registerForm";
        }
        return "registerEffect";
    }
}
