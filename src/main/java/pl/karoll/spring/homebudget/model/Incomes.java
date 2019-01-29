package pl.karoll.spring.homebudget.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Incomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Double incomeAmmount = 0.0;

    //    b - budget
    //    p - personal
    @NotNull
    @Column(nullable = false)
    private String type;

    @ManyToOne
    private User user;

    @ManyToOne
    private Budget budget;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incomes incomes = (Incomes) o;
        return Objects.equals(id, incomes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Incomes{" +
                "id=" + id +
                ", incomeAmmount=" + incomeAmmount +
                ", type='" + type + '\'' +
                '}';
    }
}
