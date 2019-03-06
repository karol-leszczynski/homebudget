package pl.karoll.spring.homebudget.dto;

import org.springframework.stereotype.Component;
import pl.karoll.spring.homebudget.model.Budget;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Component
public class ExpeneceDto {

    private Long id;

    @NotNull
    @NotBlank
    private String expenceDescription;

    @NotNull
    private Double expenceAmmount;

    private boolean payed = false;

    //    a - always
    //    o - onetime
    //    n - not predicted

    private String type;

    private Budget budget;

    private String payDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExpenceAmmount() {
        return expenceAmmount;
    }

    public void setExpenceAmmount(Double expenceAmmount) {
        this.expenceAmmount = expenceAmmount;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getExpenceDescription() {
        return expenceDescription;
    }

    public void setExpenceDescription(String expenceDescription) {
        this.expenceDescription = expenceDescription;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public LocalDate parsedPayDate (){return LocalDate.parse(payDate);}
}
