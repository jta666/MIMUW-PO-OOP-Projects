package strategieProdukcji;

import agenci.Robotnik;
import przedmioty.Przedmiot;
import com.company.HistoriaGiełdy;

import java.util.ArrayList;

public class Perspektywiczny extends StrategiaProdukcji {
    private int historiaPerspektywy;

    public Perspektywiczny(int historiaPerspektywy) {
        this.historiaPerspektywy = historiaPerspektywy;
    }

    @Override
    public Przedmiot dzisiejszyProdukt(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        ArrayList<Double> najwyższeWzrostyCen = new ArrayList<>(4);
        int dzieńPerspektywy = Math.max(historiaGiełdy.getDzień() - 1 - this.historiaPerspektywy, 0);

        for (int i = 0; i < 4; i++)
            najwyższeWzrostyCen.add(i, historiaGiełdy.getŚredniaCena(i) -
                    historiaGiełdy.getŚredniaCena(dzieńPerspektywy, i));

        int maxID = -666;
        double maxWzrost = -666.;
        for (int i = 0; i < 4; i++) {
            double obecnyWzrost = najwyższeWzrostyCen.get(i);
            if (obecnyWzrost > maxWzrost) {
                maxWzrost = obecnyWzrost;
                maxID = i;
            }
        }

        return Przedmiot.poID(maxID);
    }
}
