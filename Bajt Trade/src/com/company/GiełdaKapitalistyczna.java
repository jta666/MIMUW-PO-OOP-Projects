package com.company;

import agenci.Robotnik;
import agenci.Spekulant;
import komparatory.KapitalistycznyKomparatorOfert;

import java.util.ArrayList;

public class GiełdaKapitalistyczna extends Giełda {
    public GiełdaKapitalistyczna(int długośćGiełdy, ArrayList<Robotnik> robotnicy, ArrayList<Spekulant> spekulanci,
                  ArrayList<Double> zeroweCeny, int kara) {
        super(długośćGiełdy, robotnicy, spekulanci, zeroweCeny, kara);
    }

    public void działaj() {
        while (this.czasTrwania <= this.długośćGiełdy)
            this.tura(new KapitalistycznyKomparatorOfert());
    }
}
