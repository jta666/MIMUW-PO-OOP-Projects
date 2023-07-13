package com.company;

import agenci.Robotnik;
import agenci.Spekulant;
import komparatory.KapitalistycznyKomparatorOfert;
import komparatory.SocjalistycznyKomparatorOfert;

import java.util.ArrayList;

public class GiełdaZrównoważona extends Giełda {
    public GiełdaZrównoważona(int długośćGiełdy, ArrayList<Robotnik> robotnicy, ArrayList<Spekulant> spekulanci,
                                 ArrayList<Double> zeroweCeny, int kara) {
        super(długośćGiełdy, robotnicy, spekulanci, zeroweCeny, kara);
    }

    public void działaj() {
        while (this.czasTrwania <= this.długośćGiełdy)
            if (this.czasTrwania % 2 == 0)
                this.tura(new KapitalistycznyKomparatorOfert());
            else
                this.tura(new SocjalistycznyKomparatorOfert());
    }
}