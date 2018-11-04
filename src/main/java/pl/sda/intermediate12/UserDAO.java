package pl.sda.intermediate12;

import java.util.List;

public class UserDAO {
    private List<User> userList;


    public boolean checkIfUserExist(String eMail) {
        return userList.stream().anyMatch(u -> u.getEMail().equals(eMail));
    }


    public void saveUser(User user) {
        userList.add(user);
    }
}
