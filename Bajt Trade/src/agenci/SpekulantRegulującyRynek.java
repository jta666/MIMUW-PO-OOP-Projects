package agenci;

import przedmioty.*;
import com.company.HistoriaGiełdy;
import oferty.Oferta;
import oferty.OfertaSpekulanta;

import java.util.ArrayList;

public class SpekulantRegulującyRynek extends Spekulant {
    public SpekulantRegulującyRynek(int ID, double stanKonta, ArrayList<Pudło> przedmioty) {
        super(ID, stanKonta, przedmioty);
    }

    @Override
    public ArrayList<Oferta> wystawOfertyKupna(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> oferty = new ArrayList<>();

        if (historiaGiełdy.getDzień() == 1)
            return oferty;

        double c1 = 0.9 * historiaGiełdy.getŚredniaCena(new Jedzenie().getID())
                * historiaGiełdy.getWystawioneDzisiaj(new Jedzenie().getID())
                / Math.max(historiaGiełdy.getWystawioneWczoraj(new Jedzenie().getID()), 0);
        OfertaSpekulanta oS1 = new OfertaSpekulanta(this, new Pudło(new Jedzenie(), 100), c1);
        oferty.add(oS1);


        double c2 = 0.9 * historiaGiełdy.getŚredniaCena(new Ubranie().getID())
                * historiaGiełdy.getWystawioneDzisiaj(new Ubranie().getID())
                / Math.max(historiaGiełdy.getWystawioneWczoraj(new Ubranie().getID()), 0);
        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            OfertaSpekulanta oS2 = new OfertaSpekulanta(this, new Pudło(new Ubranie(i), 100),
                    0.9 * historiaGiełdy.getŚredniaCena(new Ubranie().getID()));
            oferty.add(oS2);
        }


        double c3 = 0.9 * historiaGiełdy.getŚredniaCena(new Narzędzie().getID())
                * historiaGiełdy.getWystawioneDzisiaj(new Narzędzie().getID())
                / Math.max(historiaGiełdy.getWystawioneWczoraj(new Narzędzie().getID()), 0);
        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            OfertaSpekulanta oS3 = new OfertaSpekulanta(this, new Pudło(new Narzędzie(i), 100),
                    0.9 * historiaGiełdy.getŚredniaCena(new Narzędzie().getID()));
            oferty.add(oS3);
        }

        double c4 = 0.9 * historiaGiełdy.getŚredniaCena(new Program().getID())
                * historiaGiełdy.getWystawioneDzisiaj(new Program().getID())
                / Math.max(historiaGiełdy.getWystawioneWczoraj(new Program().getID()), 0);
        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            OfertaSpekulanta oS2 = new OfertaSpekulanta(this, new Pudło(new Program(i), 100),
                    0.9 * historiaGiełdy.getŚredniaCena(new Program().getID()));
            oferty.add(oS2);
        }

        return oferty;
    }

    @Override
    public ArrayList<Oferta> wystawOfertySprzedaży(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> oferty = new ArrayList<>();

        if (historiaGiełdy.getDzień() == 1)
            return oferty;

        if (this.maJedzenie()) {
            double c1 = 1.1 * historiaGiełdy.getŚredniaCena(new Jedzenie().getID())
                    * historiaGiełdy.getWystawioneDzisiaj(new Jedzenie().getID())
                    / Math.max(historiaGiełdy.getWystawioneWczoraj(new Jedzenie().getID()), 0);
            int l1 = this.przedmioty.get(new Jedzenie().getID()).getLiczność();
            OfertaSpekulanta oS1 = new OfertaSpekulanta(this, new Pudło(new Jedzenie(), l1), c1);
            oferty.add(oS1);
        }

        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            if (this.maUbrania(i)) {
                double c2 = 1.1 * historiaGiełdy.getŚredniaCena(new Ubranie().getID())
                        * historiaGiełdy.getWystawioneDzisiaj(new Ubranie().getID())
                        / Math.max(historiaGiełdy.getWystawioneWczoraj(new Ubranie().getID()), 0);
                int l2 = this.przedmioty.get(new Ubranie().getID()).getLiczność();
                OfertaSpekulanta oS2 = new OfertaSpekulanta(this, new Pudło(new Ubranie(i), l2), c2);
                oferty.add(oS2);
            }
        }

        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            if (this.maNarzędzia(i)) {
                double c3 = 1.1 * historiaGiełdy.getŚredniaCena(new Narzędzie().getID())
                        * historiaGiełdy.getWystawioneDzisiaj(new Narzędzie().getID())
                        / Math.max(historiaGiełdy.getWystawioneWczoraj(new Narzędzie().getID()), 0);
                int l3 = this.przedmioty.get(new Narzędzie().getID()).getLiczność();
                OfertaSpekulanta oS3 = new OfertaSpekulanta(this, new Pudło(new Narzędzie(i), l3), c3);
                oferty.add(oS3);
            }
        }

        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            if (this.maProgramy(i)) {
                double c4 = 1.1 * historiaGiełdy.getŚredniaCena(new Program().getID())
                        * historiaGiełdy.getWystawioneDzisiaj(new Program().getID())
                        / Math.max(historiaGiełdy.getWystawioneWczoraj(new Program().getID()), 0);
                int l4 = this.przedmioty.get(new Program().getID()).getLiczność();
                OfertaSpekulanta oS4 = new OfertaSpekulanta(this, new Pudło(new Program(i), l4), c4);
                oferty.add(oS4);
            }
        }

        return oferty;
    }
}
