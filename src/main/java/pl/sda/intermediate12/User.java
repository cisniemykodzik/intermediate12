package pl.sda.intermediate12;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private UserAddress userAddress;
}
