package pl.karoll.spring.homebudget.dto;

import org.springframework.stereotype.Component;
import pl.karoll.spring.homebudget.model.Budget;
import pl.karoll.spring.homebudget.model.User;

@Component
public class IncomeDto {

    private Long id;

    private Double incomeAmmount;

    private String type;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIncomeAmmount() {
        return incomeAmmount;
    }

    public void setIncomeAmmount(Double incomeAmmount) {
        this.incomeAmmount = incomeAmmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
