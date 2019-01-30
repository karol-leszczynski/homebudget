package pl.karoll.spring.homebudget.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.UserDto;
import pl.karoll.spring.homebudget.model.User;
import pl.karoll.spring.homebudget.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private HttpSession httpSession;

    public UserService(UserRepository userRepository
            , PasswordEncoder passwordEncoder
            , HttpSession httpSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    public boolean ifExistByEmail (String email){
        if (userRepository.findByEmail(email)!=null){
            return true;
        }
        return false;
    }

    public boolean ifExistByName (String name){
        if (userRepository.findByUserName(name)!=null){
            return true;
        }
        return false;
    }

    public User currentUserByEmail (String email){
        return userRepository.findByEmail(email);
    }

    public Long currentUserIdByEmail(String email){
        return currentUserByEmail(email).getId();
    }

    public String currentUserNameByEmail(String email){
        return currentUserByEmail(email).getUserName();
    }

    public void setCurrentUserDataToSession (String currentUserEmail){
        httpSession.setAttribute("userid", currentUserIdByEmail(currentUserEmail));
        httpSession.setAttribute("name", currentUserNameByEmail(currentUserEmail));
    }

    public void saveNewUser(UserDto userDto){
        User user = new User();
        user.setEnabled(true);
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }



}
