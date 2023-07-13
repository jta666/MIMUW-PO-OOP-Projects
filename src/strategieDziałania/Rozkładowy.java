package strategieDziałania;

import agenci.Robotnik;
import com.company.HistoriaGiełdy;

import java.util.Random;

public class Rozkładowy extends StrategiaDziałania {
    @Override
    public void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        int prawdopodobieństwoNauki = 100 / (historiaGiełdy.getDzień() + 3);
        Random random = new Random();
        int wskaźnik = random.nextInt(100 + 1);
        if (wskaźnik <= prawdopodobieństwoNauki)
            r.uczSię(historiaGiełdy);
        else
            r.pracuj(historiaGiełdy);
    }
}
