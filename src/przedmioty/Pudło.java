package przedmioty;

import komparatory.JakościowyKomparatorProduktów;

import java.util.ArrayList;
import java.util.Collections;

public class Pudło {
    private Przedmiot przedmiot;
    private ArrayList<Przedmiot> przedmioty;
    private int liczność;

    public Pudło(Przedmiot przedmiot, int licznosć) {
        this.przedmiot = przedmiot;
        this.liczność = liczność;
        this.przedmioty = new ArrayList<>();
    }

    public Pudło(ArrayList<Przedmiot> przedmioty) {
        assert (przedmioty.size() > 0);
        this.przedmiot = przedmioty.get(0);
        this.liczność = przedmioty.size();
        this.przedmioty = new ArrayList<>();
        this.przedmioty.addAll(przedmioty);
        Collections.sort(this.przedmioty, new JakościowyKomparatorProduktów());
    }

    public int getLiczność() {
        return this.liczność;
    }

    public Przedmiot getPrzedmiot() {
        return this.przedmiot;
    }

    public ArrayList<Przedmiot> getPrzedmioty() {
        return this.przedmioty;
    }

    public void dodaj(int ile) {
        this.liczność += ile;
    }

    public void usuń(int ile) {
        this.liczność = Math.max(this.liczność - ile, 0);
    }

    public void dodaj(Przedmiot p) {
        this.liczność++;
        this.przedmioty.add(p);
        Collections.sort(this.przedmioty, new JakościowyKomparatorProduktów());
    }

    public void usuń(Przedmiot p) {
        this.liczność--;
        this.przedmioty.remove(p);
    }

    public ArrayList<Przedmiot> poziomu(int poziom) {
        ArrayList<Przedmiot> wynik = new ArrayList<>();
        for (int i = 0; i < this.przedmioty.size(); i++)
            if (this.przedmioty.get(i).getPoziom() == poziom)
                wynik.add(this.przedmioty.get(i));
        return wynik;
    }
}
