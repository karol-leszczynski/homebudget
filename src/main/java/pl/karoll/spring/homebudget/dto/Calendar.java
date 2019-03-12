package pl.karoll.spring.homebudget.dto;

import org.springframework.stereotype.Component;
import pl.karoll.spring.homebudget.model.Day;
import pl.karoll.spring.homebudget.model.Expences;

import java.util.List;

@Component
public class Calendar {

private List<Day> days;

private List<Expences> expiredExpences;

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<Expences> getExpiredExpences() {
        return expiredExpences;
    }

    public void setExpiredExpences(List<Expences> expiredExpences) {
        this.expiredExpences = expiredExpences;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "days=" + days +
                ", expiredExpences=" + expiredExpences +
                '}';
    }
}
