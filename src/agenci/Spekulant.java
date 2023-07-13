package agenci;

import przedmioty.Pudło;

import java.util.ArrayList;

public abstract class Spekulant extends Agent {
    public Spekulant(int ID, double stanKonta, ArrayList<Pudło> przedmioty) {
        super(ID, stanKonta, przedmioty);
    }


}
