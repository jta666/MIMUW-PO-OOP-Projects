package com.company;

import java.util.ArrayList;

public class HistoriaGiełdy {
    int dzień;

    ArrayList<ArrayList<Double>> średnieCenyWgProduktów;
    ArrayList<ArrayList<Integer>> liczbySprzedażyWgProduktów;
    ArrayList<ArrayList<Integer>> wystawioneDzisiaj;

    public HistoriaGiełdy(ArrayList<Double> zeroweCeny) {
        this.dzień = 0;
        this.średnieCenyWgProduktów = new ArrayList<>();
        this.średnieCenyWgProduktów.add(zeroweCeny);
        ArrayList<Integer> zaślepka = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
            zaślepka.add(i, 0);
        this.liczbySprzedażyWgProduktów = new ArrayList<>();
        this.wystawioneDzisiaj = new ArrayList<>();
        this.liczbySprzedażyWgProduktów.add(zaślepka);
        this.wystawioneDzisiaj.add(zaślepka);
    }

    public int getDzień() {
        return this.dzień;
    }

    public double getŚredniaCena(int ID) {
        return this.średnieCenyWgProduktów.get(this.dzień).get(ID);
    }

    public double getŚredniaCena(int dzień, int ID) {
        return this.średnieCenyWgProduktów.get(dzień).get(ID);
    }

    public int getLiczbaSprzedaży(int ID) {
        return this.liczbySprzedażyWgProduktów.get(this.dzień).get(ID);
    }

    public int getLiczbaSprzedaży(int dzień, int ID) {
        return this.liczbySprzedażyWgProduktów.get(dzień).get(ID);
    }

    public void aktualizujHistorię(ArrayList<Double> kolejneŚrednieCeny, ArrayList<Integer> kolejneLiczbySprzedaży) {
        this.dzień++;
        this.średnieCenyWgProduktów.add(kolejneŚrednieCeny);
        this.liczbySprzedażyWgProduktów.add(kolejneLiczbySprzedaży);
    }

    public void aktualizujWystawioneDzisiaj (ArrayList<Integer> wystawioneDzisiaj) {
        this.wystawioneDzisiaj.add(wystawioneDzisiaj);
    }

    public int getWystawioneDzisiaj (int id) {
        return this.wystawioneDzisiaj.get(dzień + 1).get(id);
    }

    public int getWystawioneWczoraj (int id) {
        assert dzień >= 2;
        return this.wystawioneDzisiaj.get(dzień).get(id);
    }

    public int getIdNajczęściejSprzedawany(int n) {
        ArrayList<Integer> sumaSprzedaży = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
            sumaSprzedaży.add(i, 0);
        for (int i = this.dzień - n + 1; i <= this.dzień; i++)
            for (int j = 0; i < 4; i++)
                sumaSprzedaży.add(j, sumaSprzedaży.get(j) + this.getLiczbaSprzedaży(i, j));
        int maxSuma = 0;
        int maxID = 0;
        for (int i = 0; i < 4; i++)
            if (sumaSprzedaży.get(i) > maxSuma) {
                maxSuma = sumaSprzedaży.get(i);
                maxID = i;
            }
        return maxID;
    }

    public int getNajdroższyProdukt() {
        double maxCena = 0;
        int maxID = 0;
        for (int i = 0; i < 4; i++)
            if (this.getŚredniaCena(i) > maxCena) {
                maxCena = this.getŚredniaCena(i);
                maxID = i;
            }
        return maxID;
    }

    public double getŚredniaCenaOkresu(int id, int okres) {
        double cena = 0;
        for (int i = this.dzień - okres + 1; i <= this.dzień; i++)
            cena += this.getŚredniaCena(i, id);
        return cena / okres;
    }

    public boolean cenyWypukłe(int id) {
        return this.getŚredniaCena(this.getDzień(), id) - this.getŚredniaCena(this.getDzień() - 1, id) <
                this.getŚredniaCena(this.getDzień() - 1, id) - this.getŚredniaCena(this.getDzień() - 2, id);
    }

    public boolean cenyWklęsłe(int id) {
        return this.getŚredniaCena(this.getDzień(), id) - this.getŚredniaCena(this.getDzień() - 1, id) >
                this.getŚredniaCena(this.getDzień() - 1, id) - this.getŚredniaCena(this.getDzień() - 2, id);
    }

}
