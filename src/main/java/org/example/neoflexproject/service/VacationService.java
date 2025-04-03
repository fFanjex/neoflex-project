package org.example.neoflexproject.service;

import org.example.neoflexproject.model.VacationCalculator;
import org.springframework.stereotype.Service;

@Service
public class VacationService {

    public double calculateVacation(VacationCalculator vacationCalculator) {

        double avgDaySalary = vacationCalculator.getSalary() / 12 / 29.3;
        return Math.round((avgDaySalary * vacationCalculator.getVacationDays()) * 100.0) / 100.0;
    }
}
