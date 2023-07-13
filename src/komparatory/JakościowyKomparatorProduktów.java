package komparatory;

import przedmioty.Przedmiot;

import java.util.Comparator;

public class JakościowyKomparatorProduktów implements Comparator<Przedmiot> {
    @Override
    public int compare(Przedmiot p1, Przedmiot p2) {
        if (p1.getPoziom() <= p2.getPoziom())
            return 1;
        else
            return -1;
    }
}
