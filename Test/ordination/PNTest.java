package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    private Controller controller;
    private Patient p1;
    private Laegemiddel l1;
    private PN pn1;

    @BeforeEach
    void setup() {
        controller = Controller.getController();
        p1 = controller.opretPatient("0101010101", "Fornavn Efternavn", 45);
        l1 = controller.opretLaegemiddel("Alnok", 0.5, 1, 2, "Styk");
        pn1 = controller.opretPNOrdination(LocalDate.of(2024, 04, 10), LocalDate.of(2024, 04, 20), p1, l1, 2);

    }

    @Test
    void givDosisT1() {
        boolean expected = true;
        assertEquals(expected, pn1.givDosis(LocalDate.of(2024, 04, 10)));

        ArrayList<LocalDate> expectedDates = new ArrayList<>(List.of(LocalDate.of(2024,04,10)));
        assertEquals(expectedDates, pn1.getDosisDates());
    }

    @Test
    void givDosisT2() {
        boolean expected = true;
        assertEquals(expected, pn1.givDosis(LocalDate.of(2024, 04, 15)));

        ArrayList<LocalDate> expectedDates = new ArrayList<>(List.of(LocalDate.of(2024,04,15)));
        assertEquals(expectedDates, pn1.getDosisDates());
    }

    @Test
    void givDosisT3() {
        boolean expected = true;
        assertEquals(expected, pn1.givDosis(LocalDate.of(2024, 04, 20)));

        ArrayList<LocalDate> expectedDates = new ArrayList<>(List.of(LocalDate.of(2024,04,20)));
        assertEquals(expectedDates, pn1.getDosisDates());
    }

    @Test
    void ugyldigGivDosisT1() {
        boolean expected = false;
        assertEquals(expected, pn1.givDosis(LocalDate.of(2024, 04, 9)));

        ArrayList<LocalDate> expectedDates = new ArrayList<>();
        assertEquals(expectedDates, pn1.getDosisDates());
    }

    @Test
    void ugyldigGivDosisT2() {
        boolean expected = false;
        assertEquals(expected, pn1.givDosis(LocalDate.of(2024, 04, 21)));

        ArrayList<LocalDate> expectedDates = new ArrayList<>();
        assertEquals(expectedDates, pn1.getDosisDates());
    }

    @Test
    void getAntalGangeGivetT1() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));

        int expected = 1;

        assertEquals(expected, pn1.getAntalGangeGivet());

    }
    @Test
    void getAntalGangeGivetT2() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));
        pn1.givDosis(LocalDate.of(2024, 04, 11));

        int expected = 2;

        assertEquals(expected, pn1.getAntalGangeGivet());
    }
    @Test
    void getAntalGangeGivetT3() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));
        pn1.givDosis(LocalDate.of(2024, 04, 11));
        pn1.givDosis(LocalDate.of(2024, 05, 10));

        int expected = 2;

        assertEquals(expected, pn1.getAntalGangeGivet());
    }

    @Test
    void doegnDosisT1() {
        pn1.givDosis(LocalDate.of(2024, 04, 15));

        double expected = 2;

        assertEquals(expected, pn1.doegnDosis());

    }
    @Test
    void doegnDosisT2() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));
        pn1.givDosis(LocalDate.of(2024, 04, 20));

        double expected = 0.4;

        assertEquals(expected, pn1.doegnDosis());

    }
    @Test
    void doegnDosisT3() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));
        pn1.givDosis(LocalDate.of(2024, 04, 15));
        pn1.givDosis(LocalDate.of(2024, 04, 20));

        double expected = 0.6;

        assertEquals(expected, pn1.doegnDosis());

    }
    @Test
    void doegnDosisT4() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));
        pn1.givDosis(LocalDate.of(2024, 04, 15));
        pn1.givDosis(LocalDate.of(2024, 04, 16));
        pn1.givDosis(LocalDate.of(2024, 04, 20));

        double expected = 0.8;

        assertEquals(expected, pn1.doegnDosis());

    }

    @Test
    void samletDosisT1() {
        double expected = 0;

        assertEquals(expected, pn1.samletDosis());

    }
    @Test
    void samletDosisT2() {
        pn1.givDosis(LocalDate.of(2024, 04, 15));

        double expected = 2;

        assertEquals(expected, pn1.samletDosis());

    }
    @Test
    void samletDosisT3() {
        pn1.givDosis(LocalDate.of(2024, 04, 10));
        pn1.givDosis(LocalDate.of(2024, 04, 20));

        double expected = 4;

        assertEquals(expected, pn1.samletDosis());

    }
}