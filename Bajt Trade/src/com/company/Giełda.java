package com.company;

import agenci.Robotnik;
import agenci.Spekulant;
import oferty.Oferta;
import oferty.OfertaSpekulanta;
import przedmioty.*;
import komparatory.CenowoJakościowyKomparatorOfert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Giełda {
    protected int długośćGiełdy;
    protected int czasTrwania;

    protected int kara;

    protected HistoriaGiełdy historiaGiełdy;

    protected ArrayList<Oferta> ofertySprzedażyRobotników;
    protected ArrayList<Oferta> ofertyKupnaRobotników;

    protected ArrayList<Oferta> ofertySprzedażySpekulantów;
    protected ArrayList<Oferta> ofertyKupnaSpekulantów;

    protected ArrayList<ArrayList<Oferta>> ofertyKupnaSpekulantówWgProduktu;
    protected ArrayList<ArrayList<Oferta>> ofertySprzedażySpekulantówWgProduktu;


    protected ArrayList<Robotnik> robotnicy;
    protected ArrayList<Spekulant> spekulanci;

    public Giełda(int długośćGiełdy, ArrayList<Robotnik> robotnicy, ArrayList<Spekulant> spekulanci,
                  ArrayList<Double> zeroweCeny, int kara) {
        this.kara = kara;
        this.długośćGiełdy = długośćGiełdy;
        this.czasTrwania = 0;
        this.robotnicy = robotnicy;
        this.spekulanci = spekulanci;

        this.ofertyKupnaRobotników = new ArrayList<>();
        this.ofertyKupnaSpekulantów = new ArrayList<>();
        this.ofertyKupnaSpekulantówWgProduktu = new ArrayList<>();
        this.ofertySprzedażyRobotników = new ArrayList<>();
        this.ofertySprzedażySpekulantów = new ArrayList<>();
        this.ofertySprzedażySpekulantówWgProduktu = new ArrayList<>();

        this.historiaGiełdy = new HistoriaGiełdy(zeroweCeny);
    }

    public void tura(Comparator<Oferta> komparatorOfert) {
        for (Robotnik r : robotnicy)
            r.działaj(historiaGiełdy, r);

        ArrayList<Integer> liczbySprzedaży = new ArrayList<>(4);
        ArrayList<Double> cenySprzedaży = new ArrayList<>(4);
        ArrayList<Integer> wystawioneDzisiaj = new ArrayList<>(4);

        for (Robotnik r : robotnicy) {
            ArrayList<Oferta> ofertySprzedaży = r.wystawOfertySprzedaży(historiaGiełdy);
            this.ofertySprzedażyRobotników.addAll(ofertySprzedaży);
            ArrayList<Oferta> ofertyKupna = r.wystawOfertyKupna(historiaGiełdy);
            this.ofertyKupnaRobotników.addAll(ofertyKupna);
        }

        for (Spekulant s : spekulanci) {
            ArrayList<Oferta> ofertySprzedaży = s.wystawOfertySprzedaży(historiaGiełdy);
            this.ofertyKupnaSpekulantów.addAll(ofertySprzedaży);
            ArrayList<Oferta> ofertyKupna = s.wystawOfertyKupna(historiaGiełdy);
            this.ofertyKupnaSpekulantów.addAll(ofertyKupna);
        }

        for (int i = 0; i < 4; i++) {
            ArrayList<Oferta> nowy = new ArrayList<>();
            this.ofertyKupnaSpekulantówWgProduktu.add(nowy);
            for (int j = 0; j < this.ofertyKupnaSpekulantów.size(); j++)
                if (this.ofertyKupnaSpekulantów.get(j).getPrzedmiot().getPrzedmiot().getID() == i)
                    this.ofertyKupnaSpekulantówWgProduktu.get(i).add(this.ofertyKupnaSpekulantów.get(j));
        }

        Collections.sort(this.ofertySprzedażyRobotników, komparatorOfert);
        for (int i = 0; i < 4; i++)
            Collections.sort(this.ofertyKupnaSpekulantówWgProduktu.get(i), new CenowoJakościowyKomparatorOfert());

        // musimy zapisywać do średniej ceny
        for (Oferta o : this.ofertySprzedażyRobotników) {
            ArrayList<Oferta> możliweOferty = this.ofertyKupnaSpekulantówWgProduktu.get(o.getPrzedmiot().getPrzedmiot().getID());
            for (Oferta mO : możliweOferty) {
                OfertaSpekulanta m = (OfertaSpekulanta) mO;
                if (m.getCena() < m.getAutor().getStanKonta()) {
                    int cena = (int) m.getCena();
                    int stanKonta = (int) o.getAutor().getStanKonta();
                    int ileMógłbyKupić = stanKonta / cena;
                    int ileMożeKupić = Math.max(Math.max(ileMógłbyKupić, m.getLiczność()), o.getLiczność());

                    Pudło pudłoTransakcji = new Pudło(m.getPrzedmiot().getPrzedmiot(), ileMożeKupić);
                    int id = m.getPrzedmiot().getPrzedmiot().getID();
                    wystawioneDzisiaj.add(id, wystawioneDzisiaj.get(id) + o.getLiczność());
                    int liczbaSprzedaży = ileMożeKupić;
                    double cenaSprzedaży = ileMożeKupić * m.getCena();
                    liczbySprzedaży.add(id, liczbySprzedaży.get(id) + liczbaSprzedaży);
                    cenySprzedaży.add(id, cenySprzedaży.get(id) + cenaSprzedaży);


                    m.getAutor().kup(pudłoTransakcji, m.getCena());
                    o.getAutor().sprzedaj(pudłoTransakcji, m.getCena());
                    m.usuń(pudłoTransakcji);
                    o.usuń(pudłoTransakcji);
                    if (m.getLiczność() == 0)
                        możliweOferty.remove(m);
                    if (o.getLiczność() == 0)
                        break;
                }
            }
            if (o.getLiczność() > 0)
                o.getAutor().sprzedaj(o.getPrzedmiot(),
                        this.historiaGiełdy.getŚredniaCena(o.getPrzedmiot().getPrzedmiot().getID()));
        }

        this.historiaGiełdy.aktualizujWystawioneDzisiaj(wystawioneDzisiaj);

        for (int i = 0; i < 4; i++) {
            ArrayList<Oferta> nowy = new ArrayList<>();
            this.ofertySprzedażySpekulantówWgProduktu.add(nowy);
            for (int j = 0; j < this.ofertySprzedażySpekulantów.size(); j++)
                if (this.ofertySprzedażySpekulantów.get(j).getPrzedmiot().getPrzedmiot().getID() == i)
                    this.ofertySprzedażySpekulantówWgProduktu.get(i).add(this.ofertySprzedażySpekulantów.get(j));
        }

        Collections.sort(this.ofertyKupnaRobotników, komparatorOfert);
        for (int i = 0; i < 4; i++)
            Collections.sort(this.ofertySprzedażySpekulantówWgProduktu.get(i), new CenowoJakościowyKomparatorOfert());

        for (Oferta o : this.ofertyKupnaRobotników) {
            ArrayList<Oferta> możliweOferty =
                    this.ofertySprzedażySpekulantówWgProduktu.get(o.getPrzedmiot().getPrzedmiot().getID());
            for (Oferta mO : możliweOferty) {
                assert (mO instanceof OfertaSpekulanta);
                OfertaSpekulanta m = (OfertaSpekulanta) mO;
                if (m.getCena() < o.getAutor().getStanKonta()) {
                    int cena = (int) m.getCena();
                    int stanKonta = (int) o.getAutor().getStanKonta();
                    int ileMógłbyKupić = stanKonta / cena;
                    int ileMożeKupić = Math.max(Math.max(ileMógłbyKupić, m.getLiczność()), o.getLiczność());

                    Pudło pudłoTransakcji = new Pudło(m.getPrzedmiot().getPrzedmiot(), ileMożeKupić);
                    int id = m.getPrzedmiot().getPrzedmiot().getID();
                    int liczbaSprzedaży = ileMożeKupić;
                    double cenaSprzedaży = ileMożeKupić * m.getCena();
                    liczbySprzedaży.add(id, liczbySprzedaży.get(id) + liczbaSprzedaży);
                    cenySprzedaży.add(id, cenySprzedaży.get(id) + cenaSprzedaży);

                    m.getAutor().sprzedaj(pudłoTransakcji, m.getCena());
                    o.getAutor().kup(pudłoTransakcji, m.getCena());
                    m.usuń(pudłoTransakcji);
                    o.usuń(pudłoTransakcji);
                    if (m.getLiczność() == 0)
                        możliweOferty.remove(m);
                    if (o.getLiczność() == 0)
                        break;
                }
            }
            if (o.getLiczność() > 0)
                o.getAutor().sprzedaj(o.getPrzedmiot(),
                        this.historiaGiełdy.getŚredniaCena(o.getPrzedmiot().getPrzedmiot().getID()));
        }

        ArrayList<Double> średnieCeny = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
            if (liczbySprzedaży.get(i) > 0)
                średnieCeny.add(i, cenySprzedaży.get(i) / liczbySprzedaży.get(i));
            else
                średnieCeny.add(i, historiaGiełdy.getŚredniaCena(i, 0));

        historiaGiełdy.aktualizujHistorię(średnieCeny, liczbySprzedaży);

        for (Robotnik r : robotnicy) {
            r.wyliczPremie(this.kara);
            r.zakończTurę();
        }

        this.ofertyKupnaRobotników = new ArrayList<>();
        this.ofertyKupnaSpekulantów = new ArrayList<>();
        this.ofertyKupnaSpekulantówWgProduktu = new ArrayList<>();
        this.ofertySprzedażyRobotników = new ArrayList<>();
        this.ofertySprzedażySpekulantów = new ArrayList<>();
        this.ofertySprzedażySpekulantówWgProduktu = new ArrayList<>();
        this.czasTrwania++;
    }

    public abstract void działaj();
}
