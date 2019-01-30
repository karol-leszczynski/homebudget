package pl.karoll.spring.homebudget.dto;


import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
public class UserDto {

    @NotNull
    @NotBlank(message = "To pole nie może być puste")
    private String userName;

    @NotBlank
    @Email(message = "Niepoprawny adres email")
    private String email;

    @NotNull
    @NotBlank(message = "To pole nie może być puste")
    @Size(min = 5, max = 30, message = "Hasło musi mieć od 5 znaków do 25 znaków")
    private String password;

    private String passwordSecondCheck;

    @NotNull
    @Email(message = "podaj prawidłowy adres e-mail")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSecondCheck() {
        return passwordSecondCheck;
    }

    public void setPasswordSecondCheck(String passwordSecondCheck) {
        this.passwordSecondCheck = passwordSecondCheck;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
