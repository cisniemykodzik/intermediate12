package pl.sda.intermediate12.users;

import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class UserDAO {
    private List<User> userList = new ArrayList<>(); // poradzic sobie z NPE
    private String destination = "~/users_data.txt";
    File file = new File(destination);
//
    {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            userList = (List<User>) ois.readObject();
            User standardUser = new User();
            standardUser.setEMail("user@user.pl");
            standardUser.setPasswordHash(DigestUtils.sha512Hex("user"));
            userList.add(standardUser);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean checkIfUserExist(String eMail) {
        return userList.stream().anyMatch(u -> u.getEMail().equals(eMail));
    }


    public void saveUser(User user) {
        userList.add(user);

//
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
