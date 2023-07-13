package komparatory;

import oferty.Oferta;

import java.util.Comparator;

public class SocjalistycznyKomparatorOfert implements Comparator<Oferta> {
    public int compare(Oferta o1, Oferta o2) {
        if (o1.getAutor().getStanKonta() == o2.getAutor().getStanKonta())
            return 0;
        else if (o1.getAutor().getStanKonta() > o2.getAutor().getStanKonta())
            return 1;
        else
            return -1;
    }
}
