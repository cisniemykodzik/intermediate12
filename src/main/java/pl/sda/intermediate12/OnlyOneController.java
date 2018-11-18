package pl.sda.intermediate12;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.intermediate12.categories.CategoryDTO;
import pl.sda.intermediate12.categories.CategoryService;
import pl.sda.intermediate12.users.*;
import pl.sda.intermediate12.weather.model.WeatherResult;
import pl.sda.intermediate12.weather.services.WeatherService;

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
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserContextHolder userContextHolder;

    @RequestMapping(value = "/categories")
    public String categories(@RequestParam(required = false) String searchText, Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.filterCategories(searchText);
        model.addAttribute("catsdata", categoryDTOS); //To zostanie wyslane na front
        return "catspage"; //takiego htmla bedzie szukac nasza aplikacja
    }

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/example")
    @ResponseBody
    public String example() {
        return "HELLO";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("form", new UserRegistrationDTO());
        model.addAttribute("countries", Countries.values()); // zamiast nulla przekazanie kolekcji enumow
        return "registerForm"; // -> nazwa htmla
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffect(UserRegistrationDTO userRegistrationDTO, Model model) {
        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        model.addAttribute("form", userRegistrationDTO); // tu zamiast nulla należy dodać dto z danymi usera ->
        model.addAttribute("countries", Countries.values()); // zamiast nulla przekazanie kolekcji enumow
        if (errorsMap.isEmpty()) {
            try {
                userRegistrationService.registerUser(userRegistrationDTO);
            } catch (UserExistsException e) {
                model.addAttribute("userExistsExceptionMessage", e.getMessage());// -> tu zamiast nulla należy wpisać komunikat z exceptiona pod odpowiednią nazwą(nazwę znajdźcie w htmlu;) )
                return "registerForm";
            }
        } else {
            model.addAllAttributes(errorsMap); // tu zamiast pustej mapy należy przekazać mapę z błędami
            return "registerForm";
        }
        return "registerEffect";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("form", new UserLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(UserLoginDTO userLoginDTO, Model model) {
        Boolean loggedIn = userDAO.getUserList().stream()
                .filter(u -> u.getEMail().equals(userLoginDTO.getLogin()))
                .findFirst()
                .map(u -> u.getPasswordHash().equals(DigestUtils.sha512Hex(userLoginDTO.getPassword())))
                .orElse(false);
        if (loggedIn) {
            userContextHolder.logUser(userLoginDTO.getLogin());
            return "index";
        } else {
            model.addAttribute("form", new UserLoginDTO());
            model.addAttribute("error", "Błąd logowania");
            return "loginForm";
        }
    }
<<<<<<< HEAD
=======

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @ResponseBody//wysyła dane a nie szuka htmla
    public ResponseEntity<String> weather() {
        WeatherService weatherService = new WeatherService();
        return ResponseEntity.ok(weatherService.getWeather());

    }

>>>>>>> develop
}
