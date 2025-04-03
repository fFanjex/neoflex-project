package org.example.neoflexproject;

import org.example.neoflexproject.controller.VacationController;
import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void testVacationPayWithHolidays() {
        double salary = 500000.00;
        int vacationDays = 10;
        String startDate = "2025-04-01";
        List<LocalDate> vacationDates = Arrays.asList(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 2),
                LocalDate.of(2025, 4, 3),
                LocalDate.of(2025, 4, 4),
                LocalDate.of(2025, 4, 5),
                LocalDate.of(2025, 4, 6),
                LocalDate.of(2025, 4, 7),
                LocalDate.of(2025, 4, 8),
                LocalDate.of(2025, 4, 9),
                LocalDate.of(2025, 4, 10)
        );

        double avgDaySalary = salary / 12 / 29.3;
        int paidDays = vacationDates.size() - 2;
        double expectedVacationPay = Math.round((avgDaySalary * paidDays) * 100.0) / 100.0;

        VacationCalculator vacationCalculator = new VacationCalculator(salary, vacationDays);
        when(vacationService.calculateVacationWithHolidays(eq(vacationCalculator), eq(vacationDates)))
                .thenReturn(expectedVacationPay);

        double actualVacationPay = vacationController.vacationPay(salary, vacationDays, startDate);

        assertEquals(expectedVacationPay, actualVacationPay);
    }
}
