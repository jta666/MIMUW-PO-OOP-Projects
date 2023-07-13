package kariery;

public class Kariera {
    private int ID;

    public Kariera() {
        this.ID = -666;
    }

    public Kariera(int ID) {
        this.ID = ID;
    }

    public Kariera(Kariera kariera) {
        this.ID = kariera.getID();
    }

    public int getID() {
        return this.ID;
    }
}
