package pl.sda.intermediate12.users;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Getter
@Service
public class UserDAO {
    private List<User> userList = new ArrayList<>(); // poradzic sobie z NPE


    public boolean checkIfUserExist(String eMail) {
        return userList.stream().anyMatch(u -> u.getEMail().equals(eMail));
    }


    public void saveUser(User user) {
        userList.add(user);
    }
}
