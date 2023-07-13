package przedmioty;

public class Narzędzie extends Przedmiot {
    private int poziom;

    public Narzędzie() {
        super(2);
        this.poziom = 1;
    }

    public Narzędzie(int poziom) {
        super(2);
        this.poziom = poziom;
    }
    public int getPoziom() {
        return this.poziom;
    }
}
