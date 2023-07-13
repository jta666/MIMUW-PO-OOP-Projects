package strategieDziałania;

import agenci.Robotnik;
import przedmioty.Jedzenie;
import com.company.HistoriaGiełdy;

public class Student extends StrategiaDziałania {
    private double zapas;
    private int okres;

    public Student(double zapas, int okres) {
        this.okres = okres;
        this.zapas = zapas;
    }

    @Override
    public void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        Jedzenie j = new Jedzenie();
        if (r.getStanKonta() >= this.zapas * 100 * historiaGiełdy.getŚredniaCena(j.getID(), this.okres))
            r.uczSię(historiaGiełdy);
        else
            r.pracuj(historiaGiełdy);
    }
}
