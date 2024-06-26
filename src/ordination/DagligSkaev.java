package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{

    private ArrayList<Dosis> doser = new ArrayList<>();
    public DagligSkaev(LocalDate startDen, LocalDate slutDen, LocalTime[] klokkeslet, double[] antalEnheder) {
        super(startDen, slutDen);
        for (int i = 0; i < klokkeslet.length; i++) {
            opretDosis(klokkeslet[i],antalEnheder[i]);
        }
    }


    public ArrayList<Dosis> getDoser() {
        return doser;
    }


    public void opretDosis(LocalTime tid, double antal) {
        if (antal == 0){
            throw new ArithmeticException("Antal kan ikke være 0");
        }else {
            Dosis dosis = new Dosis(tid, antal);
            doser.add(dosis);
        }
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     * @return
     */
    @Override
    public double samletDosis() {
        double samlet = doegnDosis() * antalDage();

        return samlet;
    }

    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     * @return
     */
    @Override
    public double doegnDosis() {
        double samlet = 0;
        for (Dosis dosis : doser) {
            samlet += dosis.getAntal();
        }
        return samlet;
    }

    /**
     * Returnerer ordinationstypen som en String
     * @return
     */
    @Override
    public String getType() {
        return "DagligSkaev";
    }
}
