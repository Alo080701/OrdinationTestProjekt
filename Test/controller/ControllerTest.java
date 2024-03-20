package controller;

import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;
    Patient patient20;
    Patient patient50;
    Patient patient160;
    Laegemiddel sjovPille;
    Laegemiddel alnok;


    @BeforeEach
    void setup() {

        controller = Controller.getTestController();

        patient20 = controller.opretPatient("0000000000", "Simonsen", 20);
        patient50 = controller.opretPatient("0202020202", "Larsen", 50);
        patient160 = controller.opretPatient("0101010101", "Hansen", 160);


        sjovPille = controller.opretLaegemiddel("Sjov pille", 0.1, 0.15, 0.20, "Styk");
        alnok = controller.opretLaegemiddel("Alnok", 0.5, 1, 2, "Styk");

    }

    @Test
    void opretPNOrdinationTC1() {
        PN TC1 = controller.opretPNOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 20), patient160, alnok, 2);

        PN toAssert = (PN) patient160.getOrdinationer().getFirst();

        assertEquals(TC1, toAssert);

        assertEquals(2, toAssert.getAntalEnheder());
    }

    @Test
    void opretPNOrdinationTC2() {

        PN TC2 = controller.opretPNOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 20), patient160, alnok, 4);

        PN toAssert = (PN) patient160.getOrdinationer().getFirst();

        assertEquals(TC2, toAssert);

        assertEquals(4, toAssert.getAntalEnheder());
    }

    @Test
    void opretPNOrdinationTC3() {
        PN TC3 = controller.opretPNOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 20), patient160, alnok, 6);

        PN toAssert = (PN) patient160.getOrdinationer().getFirst();

        assertEquals(TC3, toAssert);

        assertEquals(6, toAssert.getAntalEnheder());
    }


    @Test
    void anbefaletDosisPrDoegnTC1() {
        assertEquals(10, controller.anbefaletDosisPrDoegn(patient20, alnok));

    }


    @Test
    void anbefaletDosisPrDoegnTC2() {
        int vægt = 25;
        assertEquals(25, controller.anbefaletDosisPrDoegn(controller.opretPatient("0303070353", "TESTMAND", vægt), alnok));

    }

    @Test
    void anbefaletDosisPrDoegnTC3() {
        int vægt = 100;
        assertEquals(vægt, controller.anbefaletDosisPrDoegn(controller.opretPatient("0303070353", "TESTMAND", vægt), alnok));

    }

    @Test
    void anbefaletDosisPrDoegnTC4() {
        int vægt = 120;
        assertEquals(vægt, controller.anbefaletDosisPrDoegn(controller.opretPatient("0303070353", "TESTMAND", vægt), alnok));

    }

    @Test
    void anbefaletDosisPrDoegnTC5() {
        int vægt = 150;
        assertEquals(300, controller.anbefaletDosisPrDoegn(controller.opretPatient("0303070353", "TESTMAND", vægt), alnok));

    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddelTC1() {
        Patient bertil = controller.opretPatient("0202020202", "Bertil Hoffmann", 20);
        Patient johan = controller.opretPatient("3303303004", "Johan Møller", 60);
        Ordination ord1B = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 24), bertil, alnok, 5);
        Ordination ord2B = controller.opretPNOrdination(LocalDate.of(2024, 03, 10), LocalDate.of(2024, 03, 20), bertil, alnok, 5);
        Ordination ord1J = controller.opretPNOrdination(LocalDate.of(2024, 02, 05), LocalDate.of(2024, 02, 05), johan, alnok, 5);

        assertEquals(2, controller.antalOrdinationerPrVægtPrLægemiddel(1, 20, alnok));
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddelTC2() {
        Patient bertil = controller.opretPatient("0202020202", "Bertil Hoffmann", 20);
        Patient johan = controller.opretPatient("3303303004", "Johan Møller", 60);
        Ordination ord1B = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 24), bertil, alnok, 5);
        Ordination ord2B = controller.opretPNOrdination(LocalDate.of(2024, 03, 10), LocalDate.of(2024, 03, 20), bertil, alnok, 5);
        Ordination ord1J = controller.opretPNOrdination(LocalDate.of(2024, 02, 05), LocalDate.of(2024, 02, 05), johan, alnok, 5);

        assertEquals(3, controller.antalOrdinationerPrVægtPrLægemiddel(1, 100, alnok));
    }


    @Test
    void antalOrdinationerPrVægtPrLægemiddelTC3() {
        Patient bertil = controller.opretPatient("0202020202", "Bertil Hoffmann", 20);
        Patient johan = controller.opretPatient("3303303004", "Johan Møller", 60);
        Ordination ord1B = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 24), bertil, alnok, 5);
        Ordination ord2B = controller.opretPNOrdination(LocalDate.of(2024, 03, 10), LocalDate.of(2024, 03, 20), bertil, alnok, 5);
        Ordination ord1J = controller.opretPNOrdination(LocalDate.of(2024, 02, 05), LocalDate.of(2024, 02, 05), johan, alnok, 5);

        assertEquals(1, controller.antalOrdinationerPrVægtPrLægemiddel(50, 100, alnok));
    }

    @Test
    void ordinationPNAnvendtTC1() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient20, alnok, 5);

        controller.ordinationPNAnvendt(pn, LocalDate.of(2024, 01, 01));

        assertEquals(LocalDate.of(2024, 01, 01), pn.getDosisDates().getFirst());
    }

    @Test
    void ordinationPNAnvendtTC2() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient20, alnok, 5);

        controller.ordinationPNAnvendt(pn, LocalDate.of(2024, 01, 05));

        assertEquals(LocalDate.of(2024, 01, 05), pn.getDosisDates().getFirst());
    }

    @Test
    void ordinationPNAnvendtTC3() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient20, alnok, 5);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> controller.ordinationPNAnvendt(pn, LocalDate.of(2024, 01, 06)));

        assertEquals("Dato er uden for gyldighedsperiode", exception.getMessage());


    }


    @Test
    void opretDagligFastOrdinationTC1() {

        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, 2, 3, 4, 5);

        DagligFast toAssert = (DagligFast) patient160.getOrdinationer().getFirst();

        assertEquals(df, toAssert);

        assertEquals(14 * 5, toAssert.samletDosis());
    }

    @Test
    void opretDagligFastOrdinationTC2() {

        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, 3, 4, 1, 4);

        DagligFast toAssert = (DagligFast) patient160.getOrdinationer().getFirst();

        assertEquals(df, toAssert);

        assertEquals(12 * 5, toAssert.samletDosis());
    }

    @Test
    void opretDagligFastOrdinationTC3() {

        DagligFast df = controller.opretDagligFastOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, 0, 3, 1, 0);

        DagligFast toAssert = (DagligFast) patient160.getOrdinationer().getFirst();

        assertEquals(df, toAssert);

        assertEquals(4 * 5, toAssert.samletDosis());
    }


    @Test
    void OpretDagligSkaevOrdinationTC1() {

        LocalTime[] kl = {LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0)};
        double[] an = {1, 2, 4};

        DagligSkaev DS = controller.opretDagligSkaevOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, kl, an);

        DagligSkaev toAssert = (DagligSkaev) patient160.getOrdinationer().getFirst();

        assertEquals(DS, toAssert);

        assertEquals(7 * 5, toAssert.samletDosis());

    }

    @Test
    void OpretDagligSkaevOrdinationTC2() {

        LocalTime[] kl = {LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0), LocalTime.of(16, 40), LocalTime.of(20, 00)};
        double[] an = {0, 2, 4, 2, 1};

        Exception exception = assertThrows(ArithmeticException.class, () -> controller.opretDagligSkaevOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, kl, an));

        assertEquals("Antal kan ikke være 0", exception.getMessage());

    }
@Test
    void OpretDagligSkaevOrdinationTC3() {

        LocalTime[] kl = {LocalTime.of(12, 0), LocalTime.of(12, 40)};
        double[] an = {0, 2};


        Exception exception = assertThrows(ArithmeticException.class, () -> controller.opretDagligSkaevOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, kl, an));

        assertEquals("Antal kan ikke være 0", exception.getMessage());

    }
@Test
    void OpretDagligSkaevOrdinationTC4() {

        LocalTime[] kl = {LocalTime.of(12, 0), LocalTime.of(12, 40), LocalTime.of(16, 0), LocalTime.of(16, 50)};
        double[] an = {1, 1,1,1};

        DagligSkaev DS = controller.opretDagligSkaevOrdination(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 05), patient160, alnok, kl, an);

        DagligSkaev toAssert = (DagligSkaev) patient160.getOrdinationer().getFirst();

        assertEquals(DS, toAssert);

        assertEquals(4 * 5, toAssert.samletDosis());

    }



    


}