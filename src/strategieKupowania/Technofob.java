package strategieKupowania;

import agenci.Robotnik;
import przedmioty.Jedzenie;
import przedmioty.Pudło;
import com.company.HistoriaGiełdy;
import oferty.Oferta;
import oferty.OfertaRobotnika;

import java.util.ArrayList;

public class Technofob extends StrategiaKupowania {
    public ArrayList<Oferta> wystawOferty(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        ArrayList<Oferta> wynik = new ArrayList<>();
        Jedzenie j = new Jedzenie();
        Pudło p = new Pudło(j, 100);
        OfertaRobotnika oR = new OfertaRobotnika(r, p);
        wynik.add(oR);
        return wynik;
    }
}
