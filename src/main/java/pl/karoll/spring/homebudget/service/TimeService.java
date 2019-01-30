package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

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

//    public static void main(String[] args) {
//        LocalDate now = LocalDate.parse("2018-02-01");
//    }

}
