package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.domain.UserDtoOut;
import by.bntu.fitrschedule.domain.UserLoginDtoIn;
import by.bntu.fitrschedule.domain.UserRegistrationDtoIn;

public interface UserService {
    UserDtoOut login(UserLoginDtoIn userLoginDto);
    UserDtoOut registration(UserRegistrationDtoIn userRegistrationDto);
}
