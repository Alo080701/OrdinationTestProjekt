package ordination;

import java.time.LocalDate;
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
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
    // TODO
}
