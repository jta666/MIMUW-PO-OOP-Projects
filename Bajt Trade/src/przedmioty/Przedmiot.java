package przedmioty;

public abstract class Przedmiot {
    private int ID;

    public Przedmiot(int ID) {
        this.ID = ID;
    }

    public int getPoziom() {
        return 0;
    }

    public int getID() {
        return this.ID;
    }

    public static Przedmiot poID(int ID) {
        assert (ID >= 0 && ID <= 4);
        switch (ID) {
            case 0:
                return new Jedzenie();
            case 1:
                return new Ubranie();
            case 2:
                return new Narzędzie();
            case 3:
                return new Program();
            default:
                return new Diament();
        }
    }
}
