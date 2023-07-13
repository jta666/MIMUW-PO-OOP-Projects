package strategieProdukcji;

import agenci.Robotnik;
import przedmioty.Przedmiot;
import com.company.HistoriaGiełdy;

public class Krótkowzroczny extends StrategiaProdukcji {
    @Override
    public Przedmiot dzisiejszyProdukt(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        return Przedmiot.poID(historiaGiełdy.getNajdroższyProdukt());
    }
}
