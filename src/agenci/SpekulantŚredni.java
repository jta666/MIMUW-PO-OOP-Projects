package agenci;

import przedmioty.*;
import com.company.HistoriaGiełdy;
import oferty.Oferta;
import oferty.OfertaSpekulanta;

import java.util.ArrayList;

public class SpekulantŚredni extends Spekulant {
    private int historiaŚredniej;

    public SpekulantŚredni(int historiaŚredniej, int ID, double stanKonta, ArrayList<Pudło> przedmioty) {
        super(ID, stanKonta, przedmioty);
        this.historiaŚredniej = historiaŚredniej;
    }

    @Override
    public ArrayList<Oferta> wystawOfertyKupna(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> oferty = new ArrayList<>();

        if (!this.maJedzenie()) {
            OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Jedzenie(), 100),
                    0.95 * historiaGiełdy.getŚredniaCenaOkresu(new Jedzenie().getID(), this.historiaŚredniej));
            oferty.add(oS);
        } else {
            OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Jedzenie(), 100),
                    0.9 * historiaGiełdy.getŚredniaCenaOkresu(new Jedzenie().getID(), this.historiaŚredniej));
            oferty.add(oS);
        }

        if (!this.maUbrania(historiaGiełdy)) {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Ubranie(i), 100),
                        0.95 * historiaGiełdy.getŚredniaCenaOkresu(new Ubranie().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        } else {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Ubranie(i), 100),
                        0.9 * historiaGiełdy.getŚredniaCenaOkresu(new Ubranie().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        }

        if (!this.maNarzędzia(historiaGiełdy)) {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Narzędzie(i), 100),
                        0.95 * historiaGiełdy.getŚredniaCenaOkresu(new Narzędzie().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        } else {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Narzędzie(i), 100),
                        0.9 * historiaGiełdy.getŚredniaCenaOkresu(new Narzędzie().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        }

        if (!this.maProgramy(historiaGiełdy)) {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Program(i), 100),
                        0.95 * historiaGiełdy.getŚredniaCenaOkresu(new Program().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        } else {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Program(i), 100),
                        0.9 * historiaGiełdy.getŚredniaCenaOkresu(new Program().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        }

        return oferty;
    }

    @Override
    public ArrayList<Oferta> wystawOfertySprzedaży(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> oferty = new ArrayList<>();

        if (this.maJedzenie()) {
            OfertaSpekulanta oS = new OfertaSpekulanta(this,
                    new Pudło(new Jedzenie(), this.przedmioty.get(new Jedzenie().getID()).getLiczność()),
                    1.1 * historiaGiełdy.getŚredniaCenaOkresu(new Jedzenie().getID(), this.historiaŚredniej));
            oferty.add(oS);
        }

        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            if (this.maUbrania(i)) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this,
                        new Pudło(new Ubranie(i), this.przedmioty.get(new Ubranie().getID()).poziomu(i).size()),
                        1.1 * historiaGiełdy.getŚredniaCenaOkresu(new Ubranie().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        }

        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            if (this.maNarzędzia(i)) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this,
                        new Pudło(new Narzędzie(i), this.przedmioty.get(new Narzędzie().getID()).poziomu(i).size()),
                        1.1 * historiaGiełdy.getŚredniaCenaOkresu(new Narzędzie().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        }

        for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
            if (this.maProgramy(i)) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this,
                        new Pudło(new Program(i), this.przedmioty.get(new Program().getID()).poziomu(i).size()),
                        1.1 * historiaGiełdy.getŚredniaCenaOkresu(new Program().getID(), this.historiaŚredniej));
                oferty.add(oS);
            }
        }

        return oferty;
    }
}
