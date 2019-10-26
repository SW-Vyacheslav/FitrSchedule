package by.bntu.fitrschedule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginDtoIn {
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
