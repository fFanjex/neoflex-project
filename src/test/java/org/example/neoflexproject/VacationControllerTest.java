package org.example.neoflexproject;

import org.example.neoflexproject.controller.VacationController;
import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VacationControllerTest {

    @Mock
    private VacationService vacationService;

    @InjectMocks
    private VacationController vacationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVacationPay_withoutStartDate() {
        double salary = 600000.00;
        int vacationDays = 28;
        double expectedVacationPay = 57371.33;

        VacationCalculator calculator = new VacationCalculator(salary, vacationDays);
        when(vacationService.calculateVacation(calculator)).thenReturn(expectedVacationPay);

        double actualVacationPay = vacationController.vacationPay(salary, vacationDays, null);

        assertEquals(expectedVacationPay, actualVacationPay);
        verify(vacationService, times(1)).calculateVacation(calculator);
    }

    @Test
    void testVacationPay_withStartDate() {
        double salary = 500000.00;
        int vacationDays = 10;
        String startDateStr = "2025-04-01";
        LocalDate startDate = LocalDate.parse(startDateStr);

        VacationCalculator calculator = new VacationCalculator(salary, vacationDays);
        double expectedVacationPay = 15306.79;

        List<LocalDate> vacationDates = Arrays.asList(
                startDate,
                startDate.plusDays(1),
                startDate.plusDays(2),
                startDate.plusDays(3),
                startDate.plusDays(4)
        );

        when(vacationService.generateVacationDates(startDate, vacationDays)).thenReturn(vacationDates);
        when(vacationService.calculateVacationWithHolidays(eq(calculator), eq(vacationDates)))
                .thenReturn(expectedVacationPay);

        double actualVacationPay = vacationController.vacationPay(salary, vacationDays, startDateStr);

        assertEquals(expectedVacationPay, actualVacationPay);
        verify(vacationService, times(1)).generateVacationDates(startDate, vacationDays);
        verify(vacationService, times(1)).calculateVacationWithHolidays(calculator, vacationDates);
    }
}