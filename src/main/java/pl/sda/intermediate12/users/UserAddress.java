package pl.sda.intermediate12.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAddress implements Serializable {
    private static final long serialVersionUID = -8441949325468918622L;
    private String city;
    private String country;
    private String zipCode;
    private String street;
}
