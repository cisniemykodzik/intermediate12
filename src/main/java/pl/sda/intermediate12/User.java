package pl.sda.intermediate12;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String eMail;
    private String passwordHash;
    private UserAddress userAddress;
    private String birthDate;
    private String pesel;
    private String phone;
    private boolean preferEmails;

}
