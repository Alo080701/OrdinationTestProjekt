package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> dosisDates = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, double antal) {
        super(startDen, slutDen);
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
        boolean isLegit = !(givesDen.isBefore(getStartDen()) || givesDen.isAfter(getSlutDen()));

        if (isLegit) {
            dosisDates.add(givesDen);
        }

        return isLegit;
    }

    public double doegnDosis() {
        double samlet = 0;

        int dageMellem = 0;

        LocalDate førsteDosis = null;
        LocalDate sidsteDosis = null;

        if (dosisDates.size() > 1) {
            for (LocalDate dosisDate : dosisDates) {
                if (førsteDosis == null || førsteDosis.isAfter(dosisDate)) {
                    førsteDosis = dosisDate;
                } else if (sidsteDosis == null || sidsteDosis.isBefore(dosisDate)) {
                    sidsteDosis = dosisDate;
                }
            }
            dageMellem = (int) førsteDosis.until(sidsteDosis, ChronoUnit.DAYS) + 1;
            if (dageMellem == 0) {
                dageMellem++;
            }
        } else if (dosisDates.size() == 1) {
            førsteDosis = dosisDates.get(0);
            sidsteDosis = dosisDates.get(0);
            dageMellem = 1;
        }


        samlet = (dosisDates.size() * antalEnheder) / dageMellem;


        return samlet;

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
