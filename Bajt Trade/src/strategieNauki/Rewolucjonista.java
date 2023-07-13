package strategieNauki;

import agenci.Robotnik;
import kariery.*;
import com.company.HistoriaGiełdy;

public class Rewolucjonista extends StrategiaNauki {
    @Override
    public void uczSię(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        if (historiaGiełdy.getDzień() % 7 != 0)
            r.awansuj();
        else {
            int n = Math.max(1, r.getID() % 17);
            int id = historiaGiełdy.getIdNajczęściejSprzedawany(n);
            Kariera k;
            switch (id) {
                case 0:
                    k = new Rolnik();
                case 1:
                    k = new Rzemieślnik();
                case 2:
                    k = new Inżynier();
                case 3:
                    k = new Programista();
                default:
                    k = new Kariera();
            }
            if (k.getID() == r.getKariera().getID())
                r.awansuj();
            else
                r.zmieńKarierę(k);
        }
    }
}
