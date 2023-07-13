package strategieDziałania;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;

public class Pracuś extends StrategiaDziałania {
    public void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        r.pracuj(historiaGiełdy);
    }
}
