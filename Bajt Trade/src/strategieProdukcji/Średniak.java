package strategieProdukcji;

import agenci.Robotnik;
import przedmioty.Przedmiot;
import com.company.HistoriaGiełdy;

import java.util.ArrayList;

public class Średniak extends StrategiaProdukcji {
    private int historiaŚredniejProdukcji;

    public Średniak(int historiaŚredniejProdukcji) {
        this.historiaŚredniejProdukcji = historiaŚredniejProdukcji;
    }

    @Override
    public Przedmiot dzisiejszyProdukt(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        ArrayList<Double> najwyższeŚrednieCeny = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
            najwyższeŚrednieCeny.add(i, -666.);

        for (int i = historiaGiełdy.getDzień() - this.historiaŚredniejProdukcji - 1;
             i < historiaGiełdy.getDzień(); i++)
            for (int j = 0; j < 3; j++)
                if (historiaGiełdy.getŚredniaCena(i, j)
                    > najwyższeŚrednieCeny.get(j))
                    najwyższeŚrednieCeny.add(j, historiaGiełdy.getŚredniaCena(i, j));

        int maxID = -666;
        double maxCena = -666.;
        for (int i = 0; i < 4; i++) {
            double obecnaCena = najwyższeŚrednieCeny.get(i);
            if (obecnaCena > maxCena) {
                maxCena = obecnaCena;
                maxID = i;
            }
        }

        return Przedmiot.poID(maxID);
    }
}
