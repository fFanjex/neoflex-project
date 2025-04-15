package org.example.neoflexproject;

import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VacationServiceTest {

    private final VacationService vacationService = new VacationService();

    @Test
    public void testCalculateVacation() {
        VacationCalculator vacationCalculator = new VacationCalculator(50000, 14);
        double expected = 23890.78;
        double actual = vacationService.calculateVacation(vacationCalculator);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateVacation2() {
        VacationCalculator vacationCalculator = new VacationCalculator(120000, 28);
        double expected = 114675.77;
        double actual = vacationService.calculateVacation(vacationCalculator);
        assertEquals(expected, actual);
    }
}