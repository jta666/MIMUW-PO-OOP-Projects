package agenci;

import java.util.ArrayList;
import przedmioty.*;
import com.company.HistoriaGiełdy;
import oferty.Oferta;

public abstract class Agent {
    private int ID;
    protected double stanKonta;
    protected ArrayList<Pudło> przedmioty;

    public Agent(int ID, double stanKonta, ArrayList<Pudło> przedmioty) {
        this.ID = ID;
        this.stanKonta = stanKonta;
        this.przedmioty = new ArrayList<>();
        this.przedmioty.addAll(przedmioty);
    }

    public int getID() {
        return this.ID;
    }
    public double getStanKonta() {
        return this.stanKonta;
    }
    public ArrayList<Pudło> getPrzedmioty() {
        return this.przedmioty;
    }

    public boolean maJedzenie() {
        assert this.przedmioty.get(0).getLiczność() >= 0;
        return this.przedmioty.get(0).getLiczność() > 0;
    }

    public boolean maUbrania(int poziom) {
        return this.przedmioty.get(1).poziomu(poziom).size() > 0;
    }

    public boolean maUbrania(HistoriaGiełdy historiaGiełdy) {
        for (int i = 0; i <= historiaGiełdy.getDzień(); i++)
            if (maUbrania(i))
                return true;
        return false;
    }

    public boolean maNarzędzia(int poziom) {
        return this.przedmioty.get(2).poziomu(poziom).size() > 0;
    }

    public boolean maNarzędzia(HistoriaGiełdy historiaGiełdy) {
        for (int i = 0; i <= historiaGiełdy.getDzień(); i++)
            if (maNarzędzia(i))
                return true;
        return false;
    }

    public boolean maProgramy(int poziom) {
        return this.przedmioty.get(3).poziomu(poziom).size() > 0;
    }

    public boolean maProgramy(HistoriaGiełdy historiaGiełdy) {
        for (int i = 0; i <= historiaGiełdy.getDzień(); i++)
            if (maProgramy(i))
                return true;
        return false;
    }

    public void sprzedaj(Pudło p, double cena) {
        for (Przedmiot prz : p.getPrzedmioty())
            this.przedmioty.get(p.getPrzedmiot().getID()).usuń(prz);
        double nowyStanKonta = this.getStanKonta() + (cena * p.getLiczność());
        this.stanKonta = nowyStanKonta;
    }

    public void kup(Pudło p, double cena) {
        for (Przedmiot prz : p.getPrzedmioty())
            this.przedmioty.get(p.getPrzedmiot().getID()).dodaj(prz);
        double nowyStanKonta = this.getStanKonta() - (cena * p.getLiczność());
        this.stanKonta = nowyStanKonta;
        assert (this.stanKonta >= 0);
    }

    public void zużyjNarzędzia() {
        this.przedmioty.add(new Narzędzie().getID(), new Pudło(new Narzędzie(), 0));
    }

    public abstract ArrayList<Oferta> wystawOfertySprzedaży(HistoriaGiełdy historiaGiełdy);
    public abstract ArrayList<Oferta> wystawOfertyKupna(HistoriaGiełdy historiaGiełdy);
}
