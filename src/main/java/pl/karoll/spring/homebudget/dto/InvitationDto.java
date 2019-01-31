package pl.karoll.spring.homebudget.dto;

import org.springframework.stereotype.Component;
import pl.karoll.spring.homebudget.model.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Component
public class InvitationDto {


    private Long budgetId;

    private String message;

    private User sender;

    private User reciver;

    @Email(message = "Adres email jest niepoprawny")
    private String reciverEmail;

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciver() {
        return reciver;
    }

    public void setReciver(User reciver) {
        this.reciver = reciver;
    }

    public String getReciverEmail() {
        return reciverEmail;
    }

    public void setReciverEmail(String reciverEmail) {
        this.reciverEmail = reciverEmail;
    }
}
