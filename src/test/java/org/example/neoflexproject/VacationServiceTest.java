package org.example.neoflexproject;

import org.example.neoflexproject.model.VacationCalculator;
import org.example.neoflexproject.service.VacationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VacationServiceTest {

    private final VacationService vacationService = new VacationService();

    @Test
    public void testCalculateVacation() {
        VacationCalculator vacationCalculator = new VacationCalculator(600000, 28);
        double expected = 47781.57;
        double actual = vacationService.calculateVacation(vacationCalculator);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateVacation2() {
        VacationCalculator vacationCalculator = new VacationCalculator(1000000, 14);
        double expected = 39817.97;
        double actual = vacationService.calculateVacation(vacationCalculator);
        assertEquals(expected, actual);
    }
}