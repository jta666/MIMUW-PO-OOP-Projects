package oferty;

import agenci.Agent;
import przedmioty.Pudło;

public class OfertaSpekulanta extends Oferta {
    private double cena;

    public OfertaSpekulanta(Agent autor, Pudło przedmiot, double cena) {
        super(autor, przedmiot);
        this.cena = cena;
    }

    public double getCena() {
        return this.cena;
    }
}
