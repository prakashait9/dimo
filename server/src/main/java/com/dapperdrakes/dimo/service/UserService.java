package com.dapperdrakes.dimo.service;


import com.dapperdrakes.dimo.dao.UserRepository;
import com.dapperdrakes.dimo.dao.model.DiMoUser;
import com.dapperdrakes.dimo.error.UserAlreadyExistException;
import com.dapperdrakes.dimo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String APP_NAME = "SpringRegistration";

    // API

    @Override
    public DiMoUser registerNewUserAccount(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
         return userRepository.save(new DiMoUser(accountDto));
    }

    @Override
    public UserDto getUser(final String verificationToken) {
        /*final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }*/
        return null;
    }



    @Override
    public void saveRegisteredUser(final UserDto user) {

      // userRepository.save(user);
    }





   /* @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
            .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }*/



//   @Override
//    public User findUserByEmail(final String email) {
//        return userRepository.findByEmail(email);
//    }




    /*@Override
    public Optional<User> getUserByID(final long id) {
        return userRepository.findById(id);
    }*/





    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }


}
