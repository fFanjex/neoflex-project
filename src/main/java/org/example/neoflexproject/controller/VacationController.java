package org.example.neoflexproject.controller;

import lombok.AllArgsConstructor;
import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @GetMapping("/calculate")
    public double vacationPay(@RequestParam double salary, @RequestParam int vacationDays) {
        VacationCalculator vacationCalculator = new VacationCalculator(salary, vacationDays);
        return vacationService.calculateVacation(vacationCalculator);
    }
}
