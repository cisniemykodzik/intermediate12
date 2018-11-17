package pl.sda.intermediate12.users;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserValidationService {
    
    // naprawic walidacje -> by faktycznie zwracala bledy gdy trzeba + napisac testy (mega wazne)
    public Map<String, String> validateUser(UserRegistrationDTO dto) {
        Map<String, String> errorsMap = new HashMap<>();
        if (StringUtils.isBlank(dto.getFirstName()) ||
                !dto.getFirstName().trim().matches("^[A-Z][a-z]{2,}$")) {
            errorsMap.put("firstNameValRes", "Wymagane przynajmniej 3 znaki(pierwsza duża reszta z małej litery)");
        }
        if (StringUtils.isBlank(dto.getLastName()) ||
                !dto.getLastName().trim().matches("^[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?$")) {
            errorsMap.put("lastNameValRes", "Wymagane przynajmniej 3 znaki(pierwsza duża reszta z małej litery)");
        }
        if (!StringUtils.defaultIfBlank(dto.getZipCode(), "").matches("^[0-9]{2}-[0-9]{3}$")) {
            errorsMap.put("zipCodeValRes", "Zły format. Kod pocztowy powinien mieć format 12-345");
        }
        if (StringUtils.isBlank(dto.getCountry())) {
            errorsMap.put("countryValRes", "Podanie nazwy kraju jest wymagane");
        }
        if (StringUtils.isBlank(dto.getCity())) {
            errorsMap.put("cityValRes", "Podanie nazwy miasta jest wymagane");
        }
        if (StringUtils.isBlank(dto.getStreet())) {
            errorsMap.put("streetValRes", "Podanie nazwy ulicy jest wymagane");
        }
        if (!StringUtils.defaultIfBlank(dto.getBirthDate(), "").trim().matches("^(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|(1|2)[0-9]|3[0-1])$")) {
            errorsMap.put("birthDateValRes", "Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD");
        }
        if (!StringUtils.defaultIfBlank(dto.getPesel(), "").trim().matches("^\\d{11}$")) {
            errorsMap.put("peselValRes", "Zły format. Numer PESEL powinien składać się z 11 cyfr");
        }
        if (!StringUtils.defaultIfBlank(dto.getEMail(), "").trim().matches("^[\\w\\.]+@[\\w]+\\.[\\w]+(\\.[a-z]{2,3})?$")) {
            errorsMap.put("emailValRes", "Zły format adresu email");
        }
        if (!StringUtils.defaultIfBlank(dto.getPassword(), "").trim().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{10,20}")) {
            errorsMap.put("passwordValRes", "Hasło jest wymagane. Musi zawierać od 10 do 20 znaków.  -> Proponuję minimum jedna duża, jedna mała litera i cyfra.");
        }
        if (!StringUtils.defaultIfBlank(dto.getPhone(), "").trim().matches("^(\\+\\d{1,3} )?(\\d{3}-?){2}\\d{3}$")) {
            errorsMap.put("phoneValRes", "Zły format. Numer telefonu powinien składać się z 9 cyfr. -> dodajcie opcję \"+48\" na początku\" oraz xxx xxx xxx (spacje)");
        }
        return errorsMap;


    }
}
