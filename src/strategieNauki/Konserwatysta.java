package strategieNauki;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;

public class Konserwatysta extends StrategiaNauki {
    @Override
    public void uczSię(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        r.awansuj();
    }
}
