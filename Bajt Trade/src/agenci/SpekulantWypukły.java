package agenci;

import przedmioty.*;
import com.company.HistoriaGiełdy;
import oferty.Oferta;
import oferty.OfertaSpekulanta;

import java.util.ArrayList;

public class SpekulantWypukły extends Spekulant {

    public SpekulantWypukły(int ID, double stanKonta, ArrayList<Pudło> przedmioty) {
        super(ID, stanKonta, przedmioty);
    }
    @Override
    public ArrayList<Oferta> wystawOfertyKupna(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> oferty = new ArrayList<>();

        if (historiaGiełdy.cenyWypukłe(new Jedzenie().getID())) {
            OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Jedzenie(), 100),
                    0.9 * historiaGiełdy.getŚredniaCena(new Jedzenie().getID()));
            oferty.add(oS);
        }

        if (historiaGiełdy.cenyWypukłe(new Ubranie().getID())) {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Ubranie(i), 100),
                        0.9 * historiaGiełdy.getŚredniaCena(new Ubranie().getID()));
                oferty.add(oS);
            }
        }

        if (historiaGiełdy.cenyWypukłe(new Narzędzie().getID())) {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Narzędzie(i), 100),
                        0.9 * historiaGiełdy.getŚredniaCena(new Narzędzie().getID()));
                oferty.add(oS);
            }
        }

        if (historiaGiełdy.cenyWypukłe(new Program().getID())) {
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this, new Pudło(new Program(i), 100),
                        0.9 * historiaGiełdy.getŚredniaCena(new Program().getID()));
                oferty.add(oS);
            }
        }

        return oferty;
    }

    @Override
    public ArrayList<Oferta> wystawOfertySprzedaży(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> oferty = new ArrayList<>();

        if (historiaGiełdy.cenyWklęsłe(new Jedzenie().getID()))
            if (this.maJedzenie()) {
                OfertaSpekulanta oS = new OfertaSpekulanta(this,
                        new Pudło(new Jedzenie(), this.przedmioty.get(new Jedzenie().getID()).getLiczność()),
                        1.1 * historiaGiełdy.getŚredniaCena(new Jedzenie().getID()));
                oferty.add(oS);
            }

        if (historiaGiełdy.cenyWklęsłe(new Ubranie().getID()))
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                if (this.maUbrania(i)) {
                    OfertaSpekulanta oS = new OfertaSpekulanta(this,
                            new Pudło(new Ubranie(i), this.przedmioty.get(new Ubranie().getID()).poziomu(i).size()),
                            1.1 * historiaGiełdy.getŚredniaCena(new Ubranie().getID()));
                    oferty.add(oS);
                }
            }

        if (historiaGiełdy.cenyWklęsłe(new Narzędzie().getID()))
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                if (this.maNarzędzia(i)) {
                    OfertaSpekulanta oS = new OfertaSpekulanta(this,
                            new Pudło(new Ubranie(i), this.przedmioty.get(new Narzędzie().getID()).poziomu(i).size()),
                            1.1 * historiaGiełdy.getŚredniaCena(new Narzędzie().getID()));
                    oferty.add(oS);
                }
            }

        if (historiaGiełdy.cenyWklęsłe(new Program().getID()))
            for (int i = 1; i < historiaGiełdy.getDzień(); i++) {
                if (this.maProgramy(i)) {
                    OfertaSpekulanta oS = new OfertaSpekulanta(this,
                            new Pudło(new Ubranie(i), this.przedmioty.get(new Program().getID()).poziomu(i).size()),
                            1.1 * historiaGiełdy.getŚredniaCena(new Program().getID()));
                    oferty.add(oS);
                }
            }

        return oferty;
    }
}
