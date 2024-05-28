import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrapheListe implements Graphe{
    ArrayList<String> noeuds;
    ArrayList<Arcs> adjacence;

    public GrapheListe(ArrayList<String> n,ArrayList<Arcs> a){
        this.noeuds = n;
        this.adjacence =a;
    }

    @Override
    public ArrayList<String> listeNoeuds() {
        return noeuds;
    }

    @Override
    public List<Arc> suivants(String n) {
        int nb = getIndice(n);
        return adjacence.get(nb).getArcs();
    }

    public int getIndice(String n) {
        int ind = -1;
        for (int i = 0; i < this.noeuds.size(); i++) {
            if (this.noeuds.get(i).equals(n)) {
                ind = i;
            }
        }
        return ind;
    }

    public String toString() {
        String res = "";
        for (String noeud : noeuds) {
            // On ajoute le nœud
            res += noeud + " ->";
            List<Arc> suivants = suivants(noeud);
            for (Arc arc : suivants) {
                // On ajoute les nœuds suivants
                res += ' ' + arc.getDest() + '(' + arc.getCout() + ')';
            }
            res += '\n';
        }
        return res;
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
