package org.example.neoflexproject;

import org.example.neoflexproject.controller.VacationController;
import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VacationControllerTest {

    @Mock
    private VacationService vacationService;

    @InjectMocks
    private VacationController vacationController;

    public VacationControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVacationPay() {
        double salary = 600000.00;
        int vacationDays = 28;
        double avgDaySalary = salary / 12 / 29.3;
        double expectedVacationPay = Math.round((avgDaySalary * vacationDays) * 100.0) / 100.0;
        VacationCalculator vacationCalculator = new VacationCalculator(salary, vacationDays);
        when(vacationService.calculateVacation(vacationCalculator)).thenReturn(expectedVacationPay);
        double actualVacationPay = vacationController.vacationPay(salary, vacationDays);
        assertEquals(expectedVacationPay, actualVacationPay);
    }

    @Test
    void testVacationPay2() {
        double salary = 1000000.00;
        int vacationDays = 14;
        double avgDaySalary = salary / 12 / 29.3;
        double expectedVacationPay = Math.round((avgDaySalary * vacationDays) * 100.0) / 100.0;
        VacationCalculator vacationCalculator = new VacationCalculator(salary, vacationDays);
        when(vacationService.calculateVacation(vacationCalculator)).thenReturn(expectedVacationPay);
        double actualVacationPay = vacationController.vacationPay(salary, vacationDays);
        assertEquals(expectedVacationPay, actualVacationPay);
    }
}
