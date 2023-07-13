package strategieProdukcji;

import agenci.Robotnik;
import przedmioty.*;
import com.company.HistoriaGiełdy;

public class Chciwy extends StrategiaProdukcji {
    @Override
    public Przedmiot dzisiejszyProdukt(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        int najlepszyID = -666;
        int najlepszyZysk = -666;

        for (int i = 0; i < 4; i++) {
            double zysk = r.potencjalnaLiczbaProdukcji(i) * historiaGiełdy.getŚredniaCena(i);
            if (zysk > najlepszyZysk) {
                najlepszyID = i;
                najlepszyZysk = najlepszyZysk;
            }
        }

        return Przedmiot.poID(najlepszyID);
    }
}
