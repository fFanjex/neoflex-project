package org.example.neoflexproject.service;

import org.example.neoflexproject.model.VacationCalculator;
import org.springframework.stereotype.Service;

@Service
public class VacationService {

    public double calculateVacation(VacationCalculator vacationCalculator) {
        return Math.round((vacationCalculator.getSalary() / 12 / 29.3) * vacationCalculator.getVacationDays());
    }
}
