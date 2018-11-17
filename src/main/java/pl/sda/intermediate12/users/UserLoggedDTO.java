package pl.sda.intermediate12.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoggedDTO {
    private String login;

    public UserLoggedDTO(String login) {
        this.login = login;
    }
}
