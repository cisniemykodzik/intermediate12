package pl.sda.intermediate12.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAddress implements Serializable {
    private static final long serialVersionUID = -8441949325468918622L;
    private String city;
    private String country;
    private String zipCode;
    private String street;
}
