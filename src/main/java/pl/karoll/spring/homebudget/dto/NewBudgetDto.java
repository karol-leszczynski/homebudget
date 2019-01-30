package pl.karoll.spring.homebudget.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NewBudgetDto {

    private String stringDate;

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public LocalDate localDate (){
        return LocalDate.parse(stringDate);
    }

}
