package strategieKupowania;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;
import oferty.Oferta;

import java.util.ArrayList;

public abstract class StrategiaKupowania {
    public abstract ArrayList<Oferta> wystawOferty(HistoriaGiełdy historiaGiełdy, Robotnik a);

    public boolean używaProgramów() {
        return false;
    }
}
