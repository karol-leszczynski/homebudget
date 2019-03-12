package pl.karoll.spring.homebudget.service;

import org.springframework.stereotype.Service;
import pl.karoll.spring.homebudget.dto.Calendar;
import pl.karoll.spring.homebudget.dto.ExistingBudgetDto;
import pl.karoll.spring.homebudget.model.Day;
import pl.karoll.spring.homebudget.model.Expences;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private TimeService timeService;
    private ExpenceService expenceService;

    public CalendarService(TimeService timeService, ExpenceService expenceService) {
        this.timeService = timeService;
        this.expenceService = expenceService;
    }

    private List<LocalDate> days(ExistingBudgetDto budgetDto) {
        return timeService.getDaysListBetweenDates(budgetDto.getStartDate(), budgetDto.getEndDate());

    }

    private List<Day> calendarDays(ExistingBudgetDto budgetDto) {
        List<Expences> dated = expenceService.expencesWithDate(budgetDto.getExpences());
        List<Expences> expired = expenceService.expiredExpences(budgetDto.getExpences());
        List<LocalDate> days = days(budgetDto);
        List<Day> calendarDays = new ArrayList<>();
        for (int i = 1; i < timeService.daysAfterSunday(budgetDto.getStartDate()); i++) {
            calendarDays.add(new Day());
        }
        for (LocalDate day : days) {
            Day calendarDay = new Day();
            calendarDay.setShortNumber(day.format(timeService.formatterDaysOnly));
            calendarDay.setDate(day);
            if (day.isEqual(LocalDate.now())) {
                calendarDay.setToday(true);
            }
            if (dated.stream()
                    .anyMatch(d -> d.getPayDate().isEqual(day))) {
                calendarDay.setType("p");
            }
            if (expired.stream()
                    .anyMatch(d -> d.getPayDate().isEqual(day))) {
                calendarDay.setType("e");
            }
            calendarDays.add(calendarDay);
        }
        return calendarDays;
    }

    public Calendar getCalendarForBudget(ExistingBudgetDto budgetDto) {
        Calendar calendar = new Calendar();
        calendar.setExpiredExpences(expenceService.expiredExpences(budgetDto.getExpences()));
        calendar.setDays(calendarDays(budgetDto));
        return calendar;
    }

}
