package strategieProdukcji;

import agenci.Robotnik;
import przedmioty.Przedmiot;
import com.company.HistoriaGiełdy;

public abstract class StrategiaProdukcji {
    public abstract Przedmiot dzisiejszyProdukt(HistoriaGiełdy historiaGiełdy, Robotnik r);
}
