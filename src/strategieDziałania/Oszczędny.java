package strategieDziałania;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;

public class Oszczędny extends StrategiaDziałania {
    private double limitDiamentów;

    public Oszczędny(double limitDiamentów) {
        this.limitDiamentów = limitDiamentów;
    }

    public void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        if (r.getStanKonta() > this.limitDiamentów)
            r.uczSię(historiaGiełdy);
        else
            r.pracuj(historiaGiełdy);
    }
}
