package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DagligSkaevTest {

    Controller controller;
    Patient patient20;
    Patient patient50;
    Patient patient160;
    Laegemiddel sjovPille;
    Laegemiddel alnok;

    DagligSkaev dagligSkaev;
    double[] testAntalEnheder = {2,5,2,1};

    @BeforeEach
    void setup() {

        controller = Controller.getTestController();

        patient20 = controller.opretPatient("0000000000", "Simonsen", 20);
        patient50 = controller.opretPatient("0202020202", "Larsen", 50);
        patient160 = controller.opretPatient("0101010101", "Hansen", 160);

        sjovPille = controller.opretLaegemiddel("Sjov pille", 0.1, 0.15, 0.20, "Styk");
        alnok = controller.opretLaegemiddel("Alnok", 0.5, 1, 2, "Styk");

        LocalTime[] testTime = {LocalTime.of(7,30),LocalTime.of(19,0),LocalTime.of(22,30),LocalTime.of(3,0)};
        dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2024,4,7),LocalDate.of(2024,4,15),patient20,sjovPille,testTime,testAntalEnheder);

    }
    @Test
    void opretDosis() {

        double expected = dagligSkaev.getDoser().size() + 1;
        dagligSkaev.opretDosis(LocalTime.of(10,15),2);

        double actual = dagligSkaev.getDoser().size();
        assertEquals(expected,actual);

        // assertEquals(,dagligSkaev.getDoser().getLast());

    }

    @Test
    void samletDosis() {
        double expected = 0;
        double actual = dagligSkaev.samletDosis();
        assertEquals(expected,actual);

    }

    @Test
    void doegnDosis() {
        double expected = 10;
        double actual = dagligSkaev.doegnDosis();
        assertEquals(expected, actual);
    }
    @Test
    void doegnDosisTC2(){
        double[] testAntalEnheder = {4,2,1};
        LocalTime[] testTime = {LocalTime.of(7,30),LocalTime.of(19,0),LocalTime.of(22,30)};
        dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2024,4,7),LocalDate.of(2024,4,15),patient20,sjovPille,testTime,testAntalEnheder);
        double expected = 7;
        double actual = dagligSkaev.doegnDosis();
        assertEquals(expected, actual);
    }
}