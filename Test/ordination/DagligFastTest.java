package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void samletDosisLet() {
        Controller controller = Controller.getTestController();
        Patient p1 = new Patient("080701-8530", "Bertil Hoffman", 45);

    }

    @Test
    void samletDosisTung() {
        Controller controller = Controller.getTestController();
        Patient p1 = new Patient("080701-8530", "Bertil Hoffman", 45);

    }
    @Test
    void samletDosisNormalt() {
        Controller controller = Controller.getTestController();
        Patient p1 = new Patient("080701-8530", "Bertil Hoffman", 45);

    }
    @Test
    void doegnDosis() {
    }

    @Test
    void getType() {
    }
}