package pl.sda.intermediate12;

import org.apache.commons.codec.digest.DigestUtils;

public class UserRegistrationService {

    private UserDAO userDao = new UserDAO();

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        if ( userDao.checkIfUserExist(userRegistrationDTO.getEMail())){
            throw new UserExistsException("User exists!!");
        }

        userDao.saveUser(rewriteDTOToUser(userRegistrationDTO));
    }

    private User rewriteDTOToUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();

        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEMail(userRegistrationDTO.getEMail());
        user.setPassword(DigestUtils.sha512Hex(userRegistrationDTO.getPassword()));

        UserAddress userAddress = new UserAddress();
        userAddress.setCity(userRegistrationDTO.getCity());
        userAddress.setCountry(userRegistrationDTO.getCountry());
        userAddress.setStreet(userRegistrationDTO.getStreet());
        userAddress.setZipCode(userRegistrationDTO.getZipCode());

        user.setUserAddress(userAddress);
        //fixme
        return user;
    }
}
