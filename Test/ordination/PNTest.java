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
        boolean expeceted = true;

    }

    @Test
    void givDosisT3() {
        boolean expeceted = true;
    }

    @Test
    void ugyldigGivDosisT1() {
        boolean expeceted = false;
    }

    @Test
    void ugyldigGivDosisT2() {
        boolean expeceted = false;
    }
}