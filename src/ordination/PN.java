package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> dosisDates = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, double antal) {
        super(startDen, slutDen);
        this.antalEnheder = antal;
        if (antal == 0){
            throw new NullPointerException("Antal kan ikke være 0");
        }
    }

    public ArrayList<LocalDate> getDosisDates() {
        return new ArrayList<>(dosisDates);
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
        //For PN skal døgndosis beregnes som:
        //(antal gange ordinationen er anvendt * antal enheder) / (antal dage mellem første og sidste givning)
    public double doegnDosis() {

        double samlet = 0;

        double dageMellem = 0;

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
            dageMellem = (int)  DAYS.between(førsteDosis,sidsteDosis);
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
