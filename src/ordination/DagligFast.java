package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class DagligFast extends Ordination {
    private Dosis[] doser;

    public DagligFast(LocalDate startDen, LocalDate slutDen, double morgenAntal, double middagAntal, double aftenAntal,
                      double natAntal) {
        super(startDen, slutDen);
        this.doser = new Dosis[4];
        doser[0] = new Dosis(LocalTime.of(8, 0), morgenAntal);
        doser[1] = new Dosis(LocalTime.of(12, 0), middagAntal);
        doser[2] = new Dosis(LocalTime.of(18, 0), aftenAntal);
        doser[3] = new Dosis(LocalTime.of(2, 0), natAntal);
        if (morgenAntal == 0 && middagAntal == 0 && aftenAntal == 0 && natAntal == 0){
            throw new ArithmeticException("alle dosis antal kan ikke være 0");
        }

    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        double samlet = doegnDosis() * antalDage();
        return samlet;
    }

    @Override
    public double doegnDosis() {
        double samlet = 0;
        for (Dosis dosis : doser) {
            samlet += dosis.getAntal();
        }

        return samlet;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
    // TODO
}
