package strategieKupowania;

import agenci.Robotnik;
import przedmioty.*;
import com.company.HistoriaGiełdy;
import oferty.Oferta;
import oferty.OfertaRobotnika;

import java.util.ArrayList;

public class Zmechanizowany extends StrategiaKupowania {
    private int liczbaNarzędzi;

    public Zmechanizowany(int liczbaNarzędzi) {
        this.liczbaNarzędzi = liczbaNarzędzi;
    }

    public ArrayList<Oferta> wystawOferty(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        ArrayList<Oferta> wynik = new ArrayList<>();
        Jedzenie j = new Jedzenie();
        Pudło p1 = new Pudło(j, 100);
        OfertaRobotnika oR1 = new OfertaRobotnika(r, p1);
        wynik.add(oR1);

        if (r.pozostałeUbrania() < 100) {
            Ubranie u = new Ubranie();
            Pudło p2 = new Pudło(u, 100 - r.pozostałeUbrania());
            OfertaRobotnika oR2 = new OfertaRobotnika(r, p2);
            wynik.add(oR2);
        }

        Narzędzie n = new Narzędzie();
        Pudło p3 = new Pudło(n, liczbaNarzędzi);
        OfertaRobotnika oR3 = new OfertaRobotnika(r, p3);
        wynik.add(oR3);

        return wynik;
    }

}
