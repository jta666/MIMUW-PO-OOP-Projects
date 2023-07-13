package strategieDziałania;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;

public class Okresowy extends StrategiaDziałania {
    private int okres;

    public Okresowy(int okres) {
        this.okres = okres;
    }

    @Override
    public void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        if (historiaGiełdy.getDzień() % this.okres == 0)
            r.uczSię(historiaGiełdy);
        else
            r.pracuj(historiaGiełdy);
    }
}
