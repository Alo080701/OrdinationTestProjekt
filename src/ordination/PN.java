package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> dosisDates = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antal) {
        super(startDen, slutDen, laegemiddel);
        this.antalEnheder = antal;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean isLegit = !(givesDen.isBefore(getStartDen()) || givesDen.isBefore(getSlutDen()));

        if (isLegit) {
            dosisDates.add(givesDen);
        }

        return isLegit;
    }

    public double doegnDosis() {

return (double) (dosisDates.size() * antalEnheder) / (double) (getStartDen().until(getSlutDen(), ChronoUnit.DAYS));

    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return dosisDates.size() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return dosisDates.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
