package strategieProdukcji;

import agenci.Robotnik;
import przedmioty.Przedmiot;
import com.company.HistoriaGiełdy;

import java.util.Random;

public class Losowy extends StrategiaProdukcji {
    @Override
    public Przedmiot dzisiejszyProdukt(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        Random random = new Random();
        int wskaźnik = random.nextInt(5);

        return Przedmiot.poID(wskaźnik);
    }
}
