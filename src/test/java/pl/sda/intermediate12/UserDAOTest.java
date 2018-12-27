package pl.sda.intermediate12;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate12.users.User;
import pl.sda.intermediate12.users.UserDAO;

public class UserDAOTest {
    UserDAO userDAO = new UserDAO();

    @Test
    public void shouldGetUserList (){
        for (User user : userDAO.getUserList()) {
            System.out.println(user.getFirstName());
            System.out.println(user.getUserAddress().getStreet());
            System.out.println("-----------");
        }
    }
}
