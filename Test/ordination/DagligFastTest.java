package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
    private Controller controller;
    private Patient p1;
    private Laegemiddel l1;

    @BeforeEach
    void setup() {
        controller = Controller.getController();
        p1 = controller.opretPatient("0101010101","Fornavn Efternavn", 45);
        l1 = controller.opretLaegemiddel("Alnok", 0.5, 1, 2, "Styk");
    }

    @Test
    void samletDosisT1() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 2, 2, 2, 2);

        double expected = 88;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT2() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 2, 0, 0, 0);

        double expected = 22;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT3() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 2, 0, 0);

        double expected = 22;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT4() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 0, 2, 0);

        double expected = 22;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT5() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 0, 0, 2);

        double expected = 22;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT6() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 2, 2, 0, 0);

        double expected = 44;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT7() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 2, 2, 2, 0);

        double expected = 66;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT8() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,10),p1, l1, 2, 2, 2, 2);

        double expected = 8;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT9() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,05,20),p1, l1, 2, 2, 2, 2);

        double expected = 328;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void samletDosisT10() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2025,04,20),p1, l1, 2, 2, 2, 2);

        double expected = 3008;

        assertEquals(expected, df.samletDosis());

    }
    @Test
    void doegnDosisT1() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 2, 2, 2, 2);

        double expected = 8;

        assertEquals(expected, df.doegnDosis());

    }
    void doegnDosisT2() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 2, 0, 0, 0);

        double expected = 2;

        assertEquals(expected, df.doegnDosis());

    }

    void doegnDosisT3() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 2, 0, 0);

        double expected = 2;

        assertEquals(expected, df.doegnDosis());

    }
    void doegnDosisT4() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 0, 2, 0);

        double expected = 2;

        assertEquals(expected, df.doegnDosis());

    }
    void doegnDosisT5() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 0, 0, 2);

        double expected = 2;

        assertEquals(expected, df.doegnDosis());

    }
    void ugyldigTestdoegnDosis() {
        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024,04,10), LocalDate.of(2024,04,20),p1, l1, 0, 0, 0, 0);

        double expected = 0;

        assertEquals(expected, df.doegnDosis());

    }


}