package pl.karoll.spring.homebudget.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Expences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=50)
    private String expenceDescription;

    //    @NotNull
    @Column(nullable = false)
    private Double expenceAmmount = 0.0;

    @Column(nullable = false)
    private boolean payed = false;

    //    a - always
    //    o - onetime
    //    n - not predicted

    //    @NotNull
    private String type;

    private LocalDate payDate;

    @ManyToOne
    private Budget budget;

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

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public String getExpenceDescription() {
        return expenceDescription;
    }

    public void setExpenceDescription(String expenceDescription) {
        this.expenceDescription = expenceDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expences expences = (Expences) o;
        return Objects.equals(id, expences.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Expences{" +
                "id=" + id +
                ", expenceAmmount=" + expenceAmmount +
                ", payed=" + payed +
                ", type='" + type + '\'' +
                '}';
    }

}
