package pl.karoll.spring.homebudget.dto;

import org.springframework.stereotype.Component;
import pl.karoll.spring.homebudget.model.Expences;
import pl.karoll.spring.homebudget.model.Incomes;
import pl.karoll.spring.homebudget.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExistingBudgetDto {

    private Long id;

    private Double savings = 0.0;

    private LocalDate startDate;

    private LocalDate endDate;

    private int daysInMoth;

    List<User> users = new ArrayList<>();

    List<Expences> expences = new ArrayList<>();

    List<Incomes> incomes = new ArrayList<>();

    public ExistingBudgetDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDaysInMoth() {
        return daysInMoth;
    }

    public void setDaysInMoth(int daysInMoth) {
        this.daysInMoth = daysInMoth;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Expences> getExpences() {
        return expences;
    }

    public void setExpences(List<Expences> expences) {
        this.expences = expences;
    }

    public List<Incomes> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Incomes> incomes) {
        this.incomes = incomes;
    }

    @Override
    public String toString() {
        return "ExistingBudgetDto{" +
                "id=" + id +
                ", savings=" + savings +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", daysInMoth=" + daysInMoth +
                ", users=" + users +
                ", incomes=" + incomes +
                '}';
    }
}
