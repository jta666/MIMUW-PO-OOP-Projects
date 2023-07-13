package com.company;

import agenci.Robotnik;
import agenci.Spekulant;
import komparatory.SocjalistycznyKomparatorOfert;

import java.util.ArrayList;

public class GiełdaSocjalistyczna extends Giełda {
    public GiełdaSocjalistyczna(int długośćGiełdy, ArrayList<Robotnik> robotnicy, ArrayList<Spekulant> spekulanci,
                                 ArrayList<Double> zeroweCeny, int kara) {
        super(długośćGiełdy, robotnicy, spekulanci, zeroweCeny, kara);
    }

    public void działaj() {
        while (this.czasTrwania <= this.długośćGiełdy)
            this.tura(new SocjalistycznyKomparatorOfert());
    }
}