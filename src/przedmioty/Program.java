package przedmioty;

public class Program extends Przedmiot {
    private int poziom;

    public Program() {
        super(3);
        this.poziom = 1;
    }

    public Program(int poziom) {
        super(3);
        this.poziom = poziom;
    }
    public int getPoziom() {
        return this.poziom;
    }
}
