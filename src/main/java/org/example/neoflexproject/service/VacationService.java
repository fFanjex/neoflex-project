package org.example.neoflexproject.service;

import org.example.neoflexproject.model.VacationCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class VacationService {

    @Value("#{'${vacation.holidays.fixed}'.split(',')}")
    private List<String> fixedHolidays;

    public double calculateVacation(VacationCalculator vacationCalculator) {

        double avgDaySalary = vacationCalculator.getSalary() / 12 / 29.3;
        return Math.round((avgDaySalary * vacationCalculator.getVacationDays()) * 100.0) / 100.0;
    }

    public double calculateVacationWithHolidays(VacationCalculator vacationCalculator,
                                                List<LocalDate> vacationDates) {
        double avgDaySalary = vacationCalculator.getSalary() / 12 / 29.3;
        int paidDays = 0;

        for (LocalDate date : vacationDates) {
            if (!isHoliday(date)) {
                paidDays++;
            }
        }

        return Math.round((avgDaySalary * paidDays) * 100.0) / 100.0;
    }

    private boolean isHoliday(LocalDate date) {
        return fixedHolidays.contains(date.toString());
    }
}