package komparatory;

import oferty.Oferta;

import java.util.Comparator;

public class KapitalistycznyKomparatorOfert implements Comparator<Oferta> {
    public int compare(Oferta o1, Oferta o2) {
        if (o1.getAutor().getStanKonta() == o2.getAutor().getStanKonta()) {
            if (o1.getAutor().getID() != o2.getAutor().getID()) {
                if (o1.getAutor().getID() < o2.getAutor().getID())
                    return -1;
                else
                    return 1;
            } else {
                if (o1.getPrzedmiot().getPrzedmiot().getID() > o2.getPrzedmiot().getPrzedmiot().getID())
                    return 1;
                else
                    return -1;
            }
        }
        else if (o1.getAutor().getStanKonta() < o2.getAutor().getStanKonta())
            return 1;
        else
            return -1;
    }
}
