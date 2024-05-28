import java.util.ArrayList;

public class Arcs {
    private ArrayList<Arc> arcs;

    public Arcs() {
        arcs = new ArrayList<>();
    }

    public void ajouterArc(Arc a) {
        arcs.add(a);
    }

    public ArrayList<Arc> getArcs() {
        return arcs;
    }
}
