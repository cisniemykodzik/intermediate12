package pl.sda.intermediate12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate12.users.User;
import pl.sda.intermediate12.users.UserDAO;

public class UserDAOTest {
    UserDAO userDAO = new UserDAO();

    @Test
    public void shouldGetUserList (){
        for (User user : userDAO.getUserList()) {
            System.out.println(user.toString());
            System.out.println("-----------");
        }
    }
    @Test
    public void shouldGetUserListFromAlternativeMethod (){
        for (User user : userDAO.getUserListAlternative()) {
            System.out.println(user.toString());
            System.out.println("---- - - - - - ----");
        }
    }
    @Test
    public void shouldReturnSameListsUsingBothMethods () {
        Assertions.assertEquals(userDAO.getUserList(), userDAO.getUserListAlternative());
    }
}
