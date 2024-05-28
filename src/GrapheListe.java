import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe {
    ArrayList<String> noeuds;
    ArrayList<Arcs> adjacence;

    @Override
    public ArrayList<String> listeNoeuds() {
        return noeuds;
    }

    @Override
    public List<Arc> suivants(String n) {
        return null;
    }

    public int getIndice(String n) {
        int ind = -1;
        for (int i = 0; i < this.noeuds.size(); i++) {
            if (this.noeuds.get(i).compareTo(n) == 0) {
                ind = i;
            }
        }
        return ind;
    }

    public void ajouterArc(String depart, String destination, double cout) {
        int indArcDep = getIndice(depart);
        int indArcDes = getIndice(destination);
        if (indArcDes != -1 && indArcDep != -1) {
            Arcs dep = this.adjacence.get(indArcDep);
            Arc des = new Arc(destination, cout);
            dep.ajouterArc(des);
        }

    }
}
