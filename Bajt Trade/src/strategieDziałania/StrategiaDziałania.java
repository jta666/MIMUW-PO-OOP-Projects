package strategieDziałania;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;

public abstract class StrategiaDziałania {
    public abstract void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r);
}
