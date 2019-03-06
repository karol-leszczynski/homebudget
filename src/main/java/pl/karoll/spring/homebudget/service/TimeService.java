package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    public int daysInMonth (LocalDate startDate){
        Period period = Period.between(startDate, endOfMonthPeriodDate(startDate));
        return period.getDays()+1;
    }

    public LocalDate endOfMonthPeriodDate (LocalDate startDate){
        return startDate.plusMonths(1).minusDays(1);
    }

    public boolean odlerThanThisMonth (LocalDate localDate){
        LocalDate currentMonthBegin = LocalDate.now().withDayOfMonth(1);
        return localDate.isBefore(currentMonthBegin);
    }

    public LocalDate currentDate (){
        return LocalDate.now();
    }

    public LocalDateTime currentDateTime (){
        return LocalDateTime.now();
    }

    public DateTimeFormatter formatterLong = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public DateTimeFormatter formatterShort = DateTimeFormatter.ofPattern("MM-yyyy");


//    public static void main(String[] args) {
//        LocalDate now = LocalDate.parse("2018-02-01");
//    }

}
