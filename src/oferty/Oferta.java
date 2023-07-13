package oferty;

import agenci.Agent;
import przedmioty.Przedmiot;
import przedmioty.Pudło;

public abstract class Oferta {
    private Agent autor;
    private Pudło przedmiot;

    public Oferta(Agent autor, Pudło przedmiot) {
        this.autor = autor;
        this.przedmiot = przedmiot;
    }

    public Agent getAutor() {
        return this.autor;
    }

    public Pudło getPrzedmiot() {
        return this.przedmiot;
    }

    public int getLiczność() {
        return this.przedmiot.getLiczność();
    }

    public void usuń(Pudło pudłoTransakcji) {
        for (Przedmiot prz : pudłoTransakcji.getPrzedmioty())
            this.getPrzedmiot().usuń(prz);
    }
}




