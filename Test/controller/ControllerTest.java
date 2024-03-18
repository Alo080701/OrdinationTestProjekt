package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;
    Patient patient20;
    Patient patient50;
    Patient patient150;
    Laegemiddel SjovPille;


    @BeforeEach
    void setup() {
        controller = Controller.getController();

        patient20 =  controller.opretPatient("0101010101", "Carlsen", 20);
        patient50 =  controller.opretPatient("0202020202", "Larsen", 50);
        patient150 = controller.opretPatient("0303030303", "Hansen", 150);

      SjovPille = controller.opretLaegemiddel("Sjov pille", 0.1, 0.15, 0.20, "Styk");

    }

    @Test
    void opretPNOrdination() {
    }

    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkaevOrdination() {
    }

    @Test
    void ordinationPNAnvendt() {
    }

    @Test
    void anbefaletDosisPrDoegn() {
        int expected = 3;


        controller.anbefaletDosisPrDoegn(patient20,SjovPille);

    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

    @Test
    void getAllPatienter() {
    }

    @Test
    void getAllLaegemidler() {
    }

    @Test
    void opretPatient() {
    }

    @Test
    void opretLaegemiddel() {
    }

    @Test
    void createSomeObjects() {
    }
}