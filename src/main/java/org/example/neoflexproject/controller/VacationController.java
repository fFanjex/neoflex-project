package org.example.neoflexproject.controller;

import lombok.AllArgsConstructor;
import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calculate")
@AllArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @GetMapping
    public double vacationPay(@RequestParam double salary,
                              @RequestParam int vacationDays,
                              @RequestParam(required = false) String startDate) {
        VacationCalculator vacationCalculator = new VacationCalculator(salary, vacationDays);

        if (startDate == null) {
            return vacationService.calculateVacation(vacationCalculator);
        } else {
            LocalDate start = LocalDate.parse(startDate);
            List<LocalDate> vacationDates = new ArrayList<>();
            for (int i = 0; i < vacationDays; i++) {
                vacationDates.add(start.plusDays(i));
            }
            return vacationService.calculateVacationWithHolidays(vacationCalculator, vacationDates);
        }
    }
}
