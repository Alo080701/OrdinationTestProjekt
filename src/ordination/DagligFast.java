package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class DagligFast extends Ordination{
    private Dosis[] doser;
    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
        this.doser = new Dosis[4];
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        double samlet = 0;
        for (Dosis dosis : doser) {
            samlet += dosis.getAntal();
        }
        return samlet;
    }

    @Override
    public double doegnDosis() {
        double samlet = 0;
        double dage = ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
        for (Dosis dosis : doser) {
            samlet += dosis.getAntal();
        }
        return samlet/dage;
    }

    @Override
    public String getType() {
        return getType();
    }
    // TODO
}
