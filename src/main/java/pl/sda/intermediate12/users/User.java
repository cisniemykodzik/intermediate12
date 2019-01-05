package pl.sda.intermediate12.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString(exclude = "passwordHash")
@EqualsAndHashCode
public class User implements Serializable {
    private static final long serialVersionUID = -2911074201859688764L;
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
