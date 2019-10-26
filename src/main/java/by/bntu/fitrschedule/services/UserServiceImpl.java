package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.domain.*;
import by.bntu.fitrschedule.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDtoOut login(UserLoginDtoIn userLoginDto) {
        return new UserDtoOut();
    }

    @Override
    public UserDtoOut registration(UserRegistrationDtoIn userRegistrationDto) {
        User user = userRepository.findByUsername(userRegistrationDto.getUsername());
        if (user == null) {
            user = new User();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setActive(true);
            user.setUsername(userRegistrationDto.getUsername());
            user.setPassword(encoder.encode(userRegistrationDto.getPassword()).toString());
            user.setCourse(userRegistrationDto.getCourse());
            user.setGroup(userRegistrationDto.getGroup());
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        }
        else {
            return new UserDtoOut("User already exists", true);
        }

        return new UserDtoOut();
    }
}
