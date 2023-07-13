package przedmioty;

public class Ubranie extends Przedmiot {
    private int poziom;
    private int dniNoszenia;

    public Ubranie() {
        super(1);
        this.poziom = 1;
        this.dniNoszenia = 0;
    }

    public Ubranie(int poziom) {
        super(1);
        this.poziom = poziom;
        this.dniNoszenia = 0;
    }

    public int getPoziom() {
        return this.poziom;
    }

    public int getDniNoszenia() {
        return this.dniNoszenia;
    }

    public void noś() {
        this.dniNoszenia++;
    }

    public boolean zużyte() {
        return this.dniNoszenia == (this.poziom * this.poziom);
    }
}
