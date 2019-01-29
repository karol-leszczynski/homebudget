package pl.karoll.spring.homebudget.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double savings = 0.0;

    @Column(nullable = false)
    private LocalDate startDate;

    private int daysInMonth;

    private int fullWeeks;

    private int daysOutOfFullWeeks;

    @ManyToMany
    private List<User> users = new ArrayList<>();

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

    public int getDaysInMonth() {
        return daysInMonth;
    }

    public void setDaysInMonth(int daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    public int getFullWeeks() {
        return fullWeeks;
    }

    public void setFullWeeks(int fullWeeks) {
        this.fullWeeks = fullWeeks;
    }

    public int getDaysOutOfFullWeeks() {
        return daysOutOfFullWeeks;
    }

    public void setDaysOutOfFullWeeks(int daysOutOfFullWeeks) {
        this.daysOutOfFullWeeks = daysOutOfFullWeeks;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
