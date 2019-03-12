package pl.karoll.spring.homebudget.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Day {

    private LocalDate date;

    /* day types:
    - n - normal
    - p - with planned expence
    - e - with expired expence (not payed)
     */
    private String type = "n";

    private boolean today = false;

    private String shortNumber = "";

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    @Override
    public String toString() {
        return "Day{" +
                "type='" + type + '\'' +
                ", today=" + today +
                ", shortNumber='" + shortNumber + '\'' +
                '}';
    }
}
