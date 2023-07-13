package komparatory;

import oferty.Oferta;
import oferty.OfertaSpekulanta;

import java.util.Comparator;

public class CenowoJakościowyKomparatorOfert implements Comparator<Oferta> {
    public int compare(Oferta oo1, Oferta oo2) {
        assert (oo1 instanceof OfertaSpekulanta);
        assert (oo2 instanceof OfertaSpekulanta);
        OfertaSpekulanta o1 = (OfertaSpekulanta) oo1;
        OfertaSpekulanta o2 = (OfertaSpekulanta) oo2;
        if (o1.getPrzedmiot().getPrzedmiot().getPoziom() == o2.getPrzedmiot().getPrzedmiot().getPoziom()) {
            if (o1.getCena() > o2.getCena())
                return 1;
            else if (o1.getCena() < o2.getCena())
                return -1;
            else if (o1.getAutor().getID() > o2.getAutor().getID())
                return 1;
            else
                return -1;
        }
        else if (o1.getPrzedmiot().getPrzedmiot().getPoziom() < o2.getPrzedmiot().getPrzedmiot().getPoziom())
            return 1;
        else
            return -1;
    }
}

