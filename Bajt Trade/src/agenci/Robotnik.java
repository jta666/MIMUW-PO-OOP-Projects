package agenci;

import kariery.Kariera;
import kariery.Programista;
import oferty.Oferta;
import oferty.OfertaRobotnika;
import przedmioty.*;
import strategieDziałania.StrategiaDziałania;
import strategieKupowania.StrategiaKupowania;
import strategieNauki.StrategiaNauki;
import strategieProdukcji.StrategiaProdukcji;
import com.company.HistoriaGiełdy;
import oferty.Oferta;
import oferty.OfertaRobotnika;

import java.util.ArrayList;

import static java.lang.System.exit;

public class Robotnik extends Agent {
    private boolean żywy;

    private int dniBezJedzenia;
    private boolean ubrany;

    private boolean pracuje;

    private int wyprodukowaneDzisiaj;
    private Pudło wyprodukowaneDzisiajPrzedmioty;

    private final ArrayList<Integer> wektorProduktywności;
    private ArrayList<Integer> realnyWektorProduktywności;

    private Kariera kariera;
    private ArrayList<Integer> poziomyŚcieżek;
    private ArrayList<Integer> premieŚcieżek;
    private ArrayList<Integer> premieInne;

    private StrategiaKupowania strategiaKupowania;
    private StrategiaDziałania strategiaDziałania;
    private StrategiaNauki strategiaNauki;
    private StrategiaProdukcji strategiaProdukcji;

    private int premiaPoziomu(int poziom) {
        assert (poziom >= 1);
        switch (poziom) {
            case 1:
                return 50;
            case 2:
                return 150;
            case 3:
                return 300;
            default:
                return 300 + (25 * (poziom - 3));
        }
    }

    public Robotnik(int ID, double stanKonta, ArrayList<Pudło> przedmioty,
                    Kariera kariera, int poziom,
                    ArrayList<Integer> wektorProduktywności,
                    StrategiaKupowania strategiaKupowania,
                    StrategiaDziałania strategiaDziałania,
                    StrategiaNauki strategiaNauki,
                    StrategiaProdukcji strategiaProdukcji) {
        super(ID, stanKonta, przedmioty);
        this.wektorProduktywności = new ArrayList<>();
        this.wektorProduktywności.addAll(wektorProduktywności);
        this.realnyWektorProduktywności = new ArrayList<>();
        this.realnyWektorProduktywności.addAll(wektorProduktywności);
        this.strategiaKupowania = strategiaKupowania;
        this.strategiaDziałania = strategiaDziałania;
        this.strategiaNauki = strategiaNauki;
        this.strategiaProdukcji = strategiaProdukcji;

        this.żywy = true;
        this.pracuje = true;
        this.dniBezJedzenia = 0;
        this.ubrany = true;
        this.wyprodukowaneDzisiaj = 0;
        this.wyprodukowaneDzisiajPrzedmioty = null;

        this.kariera = kariera;
        this.poziomyŚcieżek = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
            this.poziomyŚcieżek.add(i, 1);
        this.poziomyŚcieżek.add(kariera.getID(), poziom);

        this.premieŚcieżek = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
            this.poziomyŚcieżek.add(i, 0);
        this.premieŚcieżek.add(kariera.getID(), premiaPoziomu(poziom));

        this.premieInne = new ArrayList<>(5);
        for (int i = 0; i < 5; i++)
            this.poziomyŚcieżek.add(i, 0);
    }

    public ArrayList<Integer> getWektorProduktywności() {
        return this.getWektorProduktywności();
    }

    public int getDniBezJedzenia() {
        return this.dniBezJedzenia;
    }

    public int getWyprodukowaneDzisiaj() {
        return this.wyprodukowaneDzisiaj;
    }

    public Kariera getKariera() {
        return this.kariera;
    }

    public ArrayList<Oferta> wystawOfertyKupna(HistoriaGiełdy historiaGiełdy) {
        return this.strategiaKupowania.wystawOferty(historiaGiełdy, this);
    }

    public ArrayList<Oferta> wystawOfertySprzedaży(HistoriaGiełdy historiaGiełdy) {
        ArrayList<Oferta> wynik = new ArrayList<>();
        OfertaRobotnika oR = new OfertaRobotnika(this, this.wyprodukowaneDzisiajPrzedmioty);
        wynik.add(oR);
        return wynik;
    }

    private boolean jedz_() {
        Jedzenie j = new Jedzenie();
        if (this.przedmioty.get(j.getID()).getLiczność() < 100)
            return false;
        this.przedmioty.get(j.getID()).usuń(100);
        return true;
    }

    public void jedz() {
        if (this.jedz_())
            return;
        this.dniBezJedzenia++;
    }

    public void jedzNaStołówce() {
        this.dniBezJedzenia = 0;
    }

    public void umrzyj() {
        this.stanKonta = 0;
        this.żywy = false;
    }

    private boolean ubierzSię_() {
        Ubranie u = new Ubranie();
        int liczbaUbrań = this.przedmioty.get(u.getID()).getLiczność();
        for (int i = 0; i < Math.min(100, liczbaUbrań); i++) {
            Przedmiot p = this.przedmioty.get(u.getID()).getPrzedmioty().get(i);
            if (p instanceof Ubranie) {
                Ubranie u2 = (Ubranie) p;
                u2.noś();
                if (u2.zużyte())
                    this.przedmioty.get(u.getID()).usuń(u2);
            } else {
                System.out.println("Ubranie nie jest ubraniem.");
                exit(0);
            }
        }
        if (liczbaUbrań < 100)
            return false;
        else
            return true;
    }

    public void ubierzSię() {
        if (this.ubierzSię_())
            this.ubrany = true;
        else
            this.ubrany = false;
    }

    public int pozostałeUbrania() {
        Ubranie u = new Ubranie();
        return this.przedmioty.get(u.getID()).getLiczność();
    }

    public void wyliczPremie(int kara) {
        if (this.pozostałeUbrania() <= 100)
            for (int i = 0; i < 5; i++)
                this.premieInne.add(i, this.premieInne.get(i) - kara);
        int głód = this.getDniBezJedzenia();
        if (głód > 0) {
            assert (głód > 0 && głód <= 2);
            switch (głód) {
                case 1:
                    for (int i = 0; i < 5; i++)
                        this.premieInne.add(i, this.premieInne.get(i) - 100);
                case 2:
                    for (int i = 0; i < 5; i++)
                        this.premieInne.add(i, this.premieInne.get(i) - 300);
            }
        }
    }

    public void działaj(HistoriaGiełdy historiaGiełdy, Robotnik r) {
        if (this.dniBezJedzenia == 3)
            this.umrzyj();
        if (this.żywy)
            this.strategiaDziałania.działaj(historiaGiełdy, this);
    }

    public void zakończTurę() {
        this.wyprodukowaneDzisiaj = 0;
        this.wyprodukowaneDzisiajPrzedmioty = new Pudło(new ArrayList<Przedmiot>());
    }

    public void pracuj(HistoriaGiełdy historiaGiełdy) {
        //this.pracuje = true;
        this.jedz();
        this.ubierzSię();

        Przedmiot p = this.strategiaProdukcji.dzisiejszyProdukt(historiaGiełdy, this);

        int liczbaProdukcji = this.potencjalnaLiczbaProdukcji(p.getID());
        this.zużyjNarzędzia();

        if (p.getID() == new Jedzenie().getID()) {
            Pudło wyprodukowaneJedzenie = new Pudło(new Jedzenie(), liczbaProdukcji);
            for (int i = 0; i < liczbaProdukcji; i++)
                wyprodukowaneJedzenie.dodaj(new Jedzenie());
            this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneJedzenie;
            this.wyprodukowaneDzisiaj = liczbaProdukcji;
        }

        if (p.getID() == new Ubranie().getID()) {
            if (!this.strategiaKupowania.używaProgramów()) {
                Pudło wyprodukowaneUbrania = new Pudło(new Ubranie(1), liczbaProdukcji);
                for (int i = 0; i < liczbaProdukcji; i++)
                    wyprodukowaneUbrania.dodaj(new Ubranie(1));
                this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneUbrania;
                this.wyprodukowaneDzisiaj = liczbaProdukcji;
            } else {
                Pudło wyprodukowaneUbrania = new Pudło(new Ubranie(1), liczbaProdukcji);
                for (Przedmiot prz : this.getPrzedmioty().get(new Program().getID()).getPrzedmioty()) {
                    assert (prz.getID() == new Program().getID());
                    Program program = (Program) prz;
                    wyprodukowaneUbrania.dodaj(new Ubranie(program.getPoziom()));
                    this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneUbrania;
                    this.wyprodukowaneDzisiaj = liczbaProdukcji;
                }
            }
        }

        if (p.getID() == new Narzędzie().getID()) {
            if (!this.strategiaKupowania.używaProgramów()) {
                Pudło wyprodukowaneNarzędzia = new Pudło(new Narzędzie(1), liczbaProdukcji);
                for (int i = 0; i < liczbaProdukcji; i++)
                    wyprodukowaneNarzędzia.dodaj(new Narzędzie(1));
                this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneNarzędzia;
                this.wyprodukowaneDzisiaj = liczbaProdukcji;
            } else {
                Pudło wyprodukowaneNarzędzia = new Pudło(new Narzędzie(1), liczbaProdukcji);
                for (Przedmiot prz : this.getPrzedmioty().get(new Program().getID()).getPrzedmioty()) {
                    assert (prz.getID() == new Program().getID());
                    Program program = (Program) prz;
                    wyprodukowaneNarzędzia.dodaj(new Narzędzie(program.getPoziom()));
                    this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneNarzędzia;
                    this.wyprodukowaneDzisiaj = liczbaProdukcji;
                }
            }
        }

        if (p.getID() == new Program().getID()) {
            if (this.getKariera().getID() != new Programista().getID()) {
                Pudło wyprodukowaneProgramy = new Pudło(new Program(1), liczbaProdukcji);
                for (int i = 0; i < liczbaProdukcji; i++)
                    wyprodukowaneProgramy.dodaj(new Program(1));
                this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneProgramy;
                this.wyprodukowaneDzisiaj = liczbaProdukcji;
            } else {
                Pudło wyprodukowaneProgramy = new Pudło(new Program(1), liczbaProdukcji);
                for (int i = 0; i < liczbaProdukcji; i++)
                    wyprodukowaneProgramy.dodaj(new Program(this.poziomyŚcieżek.get(new Programista().getID())));
                this.wyprodukowaneDzisiajPrzedmioty = wyprodukowaneProgramy;
                this.wyprodukowaneDzisiaj = liczbaProdukcji;
            }
        }

        if (p.getID() == new Diament().getID()) {
            this.wyprodukowaneDzisiaj = liczbaProdukcji;
            this.stanKonta += liczbaProdukcji;
        }
    }

    public void uczSię(HistoriaGiełdy historiaGiełdy) {
        this.jedzNaStołówce();
        this.strategiaNauki.uczSię(historiaGiełdy, this);
    }

    public int potencjalnaLiczbaProdukcji(int ID) {
        int baza = this.wektorProduktywności.get(ID);
        int premiaKariery = this.kariera.getID() == ID ? baza * (this.premieŚcieżek.get(ID)) / 100 : 0;
        int premiaInna = baza * (this.premieInne.get(ID)) / 100;
        int premiaNarzędzi = 0;
        for (Przedmiot prz : this.getPrzedmioty().get(new Narzędzie().getID()).getPrzedmioty()) {
            assert (prz.getID() == new Narzędzie().getID());
            Narzędzie n = (Narzędzie) prz;
            premiaNarzędzi += n.getPoziom();
        }
        return Math.max(baza + premiaKariery + premiaInna + premiaNarzędzi, 0);
    }

    public void awansuj() {
        int obecnyPoziom = this.poziomyŚcieżek.get(this.kariera.getID());
        obecnyPoziom++;
        this.poziomyŚcieżek.add(this.kariera.getID(), obecnyPoziom);

        int obecnaPremia = this.premieŚcieżek.get(this.kariera.getID());
        obecnaPremia = premiaPoziomu(obecnyPoziom);
        this.premieŚcieżek.add(this.kariera.getID(), obecnaPremia);
    }

    public void zmieńKarierę(Kariera k) {
        this.kariera = new Kariera(k);
    }
}
